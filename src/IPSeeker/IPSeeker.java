package IPSeeker;

/*
 * LumaQQ - Java QQ Client
 *
 * Copyright (C) 2004 luma <stubma@163.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <pre>
 * 鍏充簬IP鏁版嵁搴撴枃浠舵牸寮忥紝璇峰弬鑰僉umaQQ涓婚〉鏂囨。鈥滅函鐪烮P鏁版嵁搴撴牸寮忚瑙ｂ�濅竴鏂囥��
 * </pre>
 * 
 * @author luma
 */
public class IPSeeker {
	/**
	 * <pre>
	 * 鐢ㄦ潵灏佽ip鐩稿叧淇℃伅锛岀洰鍓嶅彧鏈変袱涓瓧娈碉紝ip鎵�鍦ㄧ殑鍥藉鍜屽湴鍖�
	 * </pre>
	 * 
	 * @author luma
	 */
	private class IPLocation {
		public String country;
		public String area;

		public IPLocation() {
			country = area = "";
		}

		public IPLocation getCopy() {
			IPLocation ret = new IPLocation();
			ret.country = country;
			ret.area = area;
			return ret;
		}
	}

	// 涓�浜涘浐瀹氬父閲忥紝姣斿璁板綍闀垮害绛夌瓑
	private static final int IP_RECORD_LENGTH = 7;
	private static final byte REDIRECT_MODE_1 = 0x01;
	private static final byte REDIRECT_MODE_2 = 0x02;

	// 鐢ㄦ潵鍋氫负cache锛屾煡璇竴涓猧p鏃堕鍏堟煡鐪媍ache锛屼互鍑忓皯涓嶅繀瑕佺殑閲嶅鏌ユ壘
	private Map<String, IPLocation> ipCache;
	// 闅忔満鏂囦欢璁块棶绫�
	private RandomAccessFile ipFile;
	// 鍐呭瓨鏄犲皠鏂囦欢
	private MappedByteBuffer mbb;

	// 璧峰鍦板尯鐨勫紑濮嬪拰缁撴潫鐨勭粷瀵瑰亸绉�
	private long ipBegin, ipEnd;
	// 涓烘彁楂樻晥鐜囪�岄噰鐢ㄧ殑涓存椂鍙橀噺
	private IPLocation loc;
	private byte[] buf;
	private byte[] b4;
	private byte[] b3;

	private static Random random = new Random(System.currentTimeMillis());

	private boolean extractResource(File tempFile) {
		boolean re = false;
		InputStream input = null;
		FileOutputStream output = null;
		try {
			input = IPSeeker.class.getResourceAsStream("QQWry.Dat");
			output = new FileOutputStream(tempFile);
			byte[] buff = new byte[1024];
			int len;
			while ((len = input.read(buff)) >= 0) {
				output.write(buff, 0, len);
			}
			re = true;
		} catch (IOException e) {
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					re = false;
				}
			}
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					re = false;
				}
			}
		}

		return re;
	}

	/**
	 * 绉佹湁鏋勯�犲嚱鏁�
	 */
	public IPSeeker() {
		ipCache = new HashMap<String, IPLocation>();
		loc = new IPLocation();
		buf = new byte[100];
		b4 = new byte[4];
		b3 = new byte[3];
		try {
			File tempFile = File.createTempFile(Long.toString(System
					.currentTimeMillis()
					+ random.nextLong()), null);
			tempFile.deleteOnExit();

			if (extractResource(tempFile)) {
				ipFile = new RandomAccessFile(tempFile, "r");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 濡傛灉鎵撳紑鏂囦欢鎴愬姛锛岃鍙栨枃浠跺ご淇℃伅
		if (ipFile != null) {
			try {
				ipBegin = readLong4(0);
				ipEnd = readLong4(4);
				if (ipBegin == -1 || ipEnd == -1) {
					ipFile.close();
					ipFile = null;
				}
			} catch (IOException e) {
				ipFile = null;
			}
		}
	}

	/**
	 * 缁欏畾涓�涓湴鐐圭殑涓嶅畬鍏ㄥ悕瀛楋紝寰楀埌涓�绯诲垪鍖呭惈s瀛愪覆鐨処P鑼冨洿璁板綍
	 * 
	 * @param s
	 *            鍦扮偣瀛愪覆
	 * @return 鍖呭惈IPEntry绫诲瀷鐨凩ist
	 */
	public List<IPEntry> getIPEntriesDebug(String s) {
		List<IPEntry> ret = new ArrayList<IPEntry>();
		long endOffset = ipEnd + 4;
		for (long offset = ipBegin + 4; offset <= endOffset; offset += IP_RECORD_LENGTH) {
			// 璇诲彇缁撴潫IP鍋忕Щ
			long temp = readLong3(offset);
			// 濡傛灉temp涓嶇瓑浜�-1锛岃鍙朓P鐨勫湴鐐逛俊鎭�
			if (temp != -1) {
				IPLocation ipLoc = getIPLocation(temp);
				// 鍒ゆ柇鏄惁杩欎釜鍦扮偣閲岄潰鍖呭惈浜唖瀛愪覆锛屽鏋滃寘鍚簡锛屾坊鍔犺繖涓褰曞埌List涓紝濡傛灉娌℃湁锛岀户缁�
				if (ipLoc.country.indexOf(s) != -1
						|| ipLoc.area.indexOf(s) != -1) {
					IPEntry entry = new IPEntry();
					entry.country = ipLoc.country;
					entry.area = ipLoc.area;
					// 寰楀埌璧峰IP
					readIP(offset - 4, b4);
					entry.beginIp = Util.getIpStringFromBytes(b4);
					// 寰楀埌缁撴潫IP
					readIP(temp, b4);
					entry.endIp = Util.getIpStringFromBytes(b4);
					// 娣诲姞璇ヨ褰�
					ret.add(entry);
				}
			}
		}
		return ret;
	}

	/**
	 * 缁欏畾涓�涓湴鐐圭殑涓嶅畬鍏ㄥ悕瀛楋紝寰楀埌涓�绯诲垪鍖呭惈s瀛愪覆鐨処P鑼冨洿璁板綍
	 * 
	 * @param s
	 *            鍦扮偣瀛愪覆
	 * @return 鍖呭惈IPEntry绫诲瀷鐨凩ist
	 */
	public List<IPEntry> getIPEntries(String s) {
		List<IPEntry> ret = new ArrayList<IPEntry>();
		try {
			// 鏄犲皠IP淇℃伅鏂囦欢鍒板唴瀛樹腑
			if (mbb == null) {
				FileChannel fc = ipFile.getChannel();
				mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, ipFile.length());
				mbb.order(ByteOrder.LITTLE_ENDIAN);
			}

			int endOffset = (int) ipEnd;
			for (int offset = (int) ipBegin + 4; offset <= endOffset; offset += IP_RECORD_LENGTH) {
				int temp = readInt3(offset);
				if (temp != -1) {
					IPLocation ipLoc = getIPLocation(temp);
					// 鍒ゆ柇鏄惁杩欎釜鍦扮偣閲岄潰鍖呭惈浜唖瀛愪覆锛屽鏋滃寘鍚簡锛屾坊鍔犺繖涓褰曞埌List涓紝濡傛灉娌℃湁锛岀户缁�
					if (ipLoc.country.indexOf(s) != -1
							|| ipLoc.area.indexOf(s) != -1) {
						IPEntry entry = new IPEntry();
						entry.country = ipLoc.country;
						entry.area = ipLoc.area;
						// 寰楀埌璧峰IP
						readIP(offset - 4, b4);
						entry.beginIp = Util.getIpStringFromBytes(b4);
						// 寰楀埌缁撴潫IP
						readIP(temp, b4);
						entry.endIp = Util.getIpStringFromBytes(b4);
						// 娣诲姞璇ヨ褰�
						ret.add(entry);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 浠庡唴瀛樻槧灏勬枃浠剁殑offset浣嶇疆寮�濮嬬殑3涓瓧鑺傝鍙栦竴涓猧nt
	 * 
	 * @param offset
	 * @return
	 */
	private int readInt3(int offset) {
		mbb.position(offset);
		return mbb.getInt() & 0x00FFFFFF;
	}

	/**
	 * 浠庡唴瀛樻槧灏勬枃浠剁殑褰撳墠浣嶇疆寮�濮嬬殑3涓瓧鑺傝鍙栦竴涓猧nt
	 * 
	 * @return
	 */
	private int readInt3() {
		return mbb.getInt() & 0x00FFFFFF;
	}

	/**
	 * 鏍规嵁IP寰楀埌鍥藉鍚�
	 * 
	 * @param ip
	 *            ip鐨勫瓧鑺傛暟缁勫舰寮�
	 * @return 鍥藉鍚嶅瓧绗︿覆
	 */
	public String getCountry(byte[] ip) {
		// 妫�鏌p鍦板潃鏂囦欢鏄惁姝ｅ父
		if (ipFile == null)
			return "闈炴硶鐨処P鏂囦欢";
		// 淇濆瓨ip锛岃浆鎹p瀛楄妭鏁扮粍涓哄瓧绗︿覆褰㈠紡
		String ipStr = Util.getIpStringFromBytes(ip);
		// 鍏堟鏌ache涓槸鍚﹀凡缁忓寘鍚湁杩欎釜ip鐨勭粨鏋滐紝娌℃湁鍐嶆悳绱㈡枃浠�
		if (ipCache.containsKey(ipStr)) {
			IPLocation ipLoc = ipCache.get(ipStr);
			return ipLoc.country;
		} else {
			IPLocation ipLoc = getIPLocation(ip);
			ipCache.put(ipStr, ipLoc.getCopy());
			return ipLoc.country;
		}
	}

	/**
	 * 鏍规嵁IP寰楀埌鍥藉鍚�
	 * 
	 * @param ip
	 *            IP鐨勫瓧绗︿覆褰㈠紡
	 * @return 鍥藉鍚嶅瓧绗︿覆
	 */
	public String getCountry(String ip) {
		return getCountry(Util.getIpByteArrayFromString(ip));
	}

	/**
	 * 鏍规嵁IP寰楀埌鍦板尯鍚�
	 * 
	 * @param ip
	 *            ip鐨勫瓧鑺傛暟缁勫舰寮�
	 * @return 鍦板尯鍚嶅瓧绗︿覆
	 */
	public String getArea(byte[] ip) {
		// 妫�鏌p鍦板潃鏂囦欢鏄惁姝ｅ父
		if (ipFile == null)
			return "闈炴硶鐨処P鏂囦欢";
		// 淇濆瓨ip锛岃浆鎹p瀛楄妭鏁扮粍涓哄瓧绗︿覆褰㈠紡
		String ipStr = Util.getIpStringFromBytes(ip);
		// 鍏堟鏌ache涓槸鍚﹀凡缁忓寘鍚湁杩欎釜ip鐨勭粨鏋滐紝娌℃湁鍐嶆悳绱㈡枃浠�
		if (ipCache.containsKey(ipStr)) {
			IPLocation ipLoc = ipCache.get(ipStr);
			return ipLoc.area;
		} else {
			IPLocation ipLoc = getIPLocation(ip);
			ipCache.put(ipStr, ipLoc.getCopy());
			return ipLoc.area;
		}
	}

	/**
	 * 鏍规嵁IP寰楀埌鍦板尯鍚�
	 * 
	 * @param ip
	 *            IP鐨勫瓧绗︿覆褰㈠紡
	 * @return 鍦板尯鍚嶅瓧绗︿覆
	 */
	public String getArea(String ip) {
		return getArea(Util.getIpByteArrayFromString(ip));
	}

	/**
	 * 鏍规嵁ip鎼滅储ip淇℃伅鏂囦欢锛屽緱鍒癐PLocation缁撴瀯锛屾墍鎼滅储鐨刬p鍙傛暟浠庣被鎴愬憳ip涓緱鍒�
	 * 
	 * @param ip
	 *            瑕佹煡璇㈢殑IP
	 * @return IPLocation缁撴瀯
	 */
	private IPLocation getIPLocation(byte[] ip) {
		IPLocation info = null;
		long offset = locateIP(ip);
		if (offset != -1)
			info = getIPLocation(offset);
		if (info == null) {
			info = new IPLocation();
			info.country = "鏈煡鍥藉";
			info.area = "鏈煡鍦板尯";
		}
		return info;
	}

	/**
	 * 浠巓ffset浣嶇疆璇诲彇4涓瓧鑺備负涓�涓猯ong锛屽洜涓簀ava涓篵ig-endian鏍煎紡锛屾墍浠ユ病鍔炴硶 鐢ㄤ簡杩欎箞涓�涓嚱鏁版潵鍋氳浆鎹�
	 * 
	 * @param offset
	 * @return 璇诲彇鐨刲ong鍊硷紝杩斿洖-1琛ㄧず璇诲彇鏂囦欢澶辫触
	 */
	private long readLong4(long offset) {
		long ret = 0;
		try {
			ipFile.seek(offset);
			ret |= (ipFile.readByte() & 0xFF);
			ret |= ((ipFile.readByte() << 8) & 0xFF00);
			ret |= ((ipFile.readByte() << 16) & 0xFF0000);
			ret |= ((ipFile.readByte() << 24) & 0xFF000000);
			return ret;
		} catch (IOException e) {
			return -1;
		}
	}

	/**
	 * 浠巓ffset浣嶇疆璇诲彇3涓瓧鑺備负涓�涓猯ong锛屽洜涓簀ava涓篵ig-endian鏍煎紡锛屾墍浠ユ病鍔炴硶 鐢ㄤ簡杩欎箞涓�涓嚱鏁版潵鍋氳浆鎹�
	 * 
	 * @param offset
	 *            鏁存暟鐨勮捣濮嬪亸绉�
	 * @return 璇诲彇鐨刲ong鍊硷紝杩斿洖-1琛ㄧず璇诲彇鏂囦欢澶辫触
	 */
	private long readLong3(long offset) {
		long ret = 0;
		try {
			ipFile.seek(offset);
			ipFile.readFully(b3);
			ret |= (b3[0] & 0xFF);
			ret |= ((b3[1] << 8) & 0xFF00);
			ret |= ((b3[2] << 16) & 0xFF0000);
			return ret;
		} catch (IOException e) {
			return -1;
		}
	}

	/**
	 * 浠庡綋鍓嶄綅缃鍙�3涓瓧鑺傝浆鎹㈡垚long
	 * 
	 * @return 璇诲彇鐨刲ong鍊硷紝杩斿洖-1琛ㄧず璇诲彇鏂囦欢澶辫触
	 */
	private long readLong3() {
		long ret = 0;
		try {
			ipFile.readFully(b3);
			ret |= (b3[0] & 0xFF);
			ret |= ((b3[1] << 8) & 0xFF00);
			ret |= ((b3[2] << 16) & 0xFF0000);
			return ret;
		} catch (IOException e) {
			return -1;
		}
	}

	/**
	 * 浠巓ffset浣嶇疆璇诲彇鍥涗釜瀛楄妭鐨刬p鍦板潃鏀惧叆ip鏁扮粍涓紝璇诲彇鍚庣殑ip涓篵ig-endian鏍煎紡锛屼絾鏄�
	 * 鏂囦欢涓槸little-endian褰㈠紡锛屽皢浼氳繘琛岃浆鎹�
	 * 
	 * @param offset
	 * @param ip
	 */
	private void readIP(long offset, byte[] ip) {
		try {
			ipFile.seek(offset);
			ipFile.readFully(ip);
			byte temp = ip[0];
			ip[0] = ip[3];
			ip[3] = temp;
			temp = ip[1];
			ip[1] = ip[2];
			ip[2] = temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 浠巓ffset浣嶇疆璇诲彇鍥涗釜瀛楄妭鐨刬p鍦板潃鏀惧叆ip鏁扮粍涓紝璇诲彇鍚庣殑ip涓篵ig-endian鏍煎紡锛屼絾鏄�
	 * 鏂囦欢涓槸little-endian褰㈠紡锛屽皢浼氳繘琛岃浆鎹�
	 * 
	 * @param offset
	 * @param ip
	 */
	private void readIP(int offset, byte[] ip) {
		mbb.position(offset);
		mbb.get(ip);
		byte temp = ip[0];
		ip[0] = ip[3];
		ip[3] = temp;
		temp = ip[1];
		ip[1] = ip[2];
		ip[2] = temp;
	}

	/**
	 * 鎶婄被鎴愬憳ip鍜宐eginIp姣旇緝锛屾敞鎰忚繖涓猙eginIp鏄痓ig-endian鐨�
	 * 
	 * @param ip
	 *            瑕佹煡璇㈢殑IP
	 * @param beginIp
	 *            鍜岃鏌ヨIP鐩告瘮杈冪殑IP
	 * @return 鐩哥瓑杩斿洖0锛宨p澶т簬beginIp鍒欒繑鍥�1锛屽皬浜庤繑鍥�-1銆�
	 */
	private int compareIP(byte[] ip, byte[] beginIp) {
		for (int i = 0; i < 4; i++) {
			int r = compareByte(ip[i], beginIp[i]);
			if (r != 0)
				return r;
		}
		return 0;
	}

	/**
	 * 鎶婁袱涓猙yte褰撲綔鏃犵鍙锋暟杩涜姣旇緝
	 * 
	 * @param b1
	 * @param b2
	 * @return 鑻1澶т簬b2鍒欒繑鍥�1锛岀浉绛夎繑鍥�0锛屽皬浜庤繑鍥�-1
	 */
	private int compareByte(byte b1, byte b2) {
		if ((b1 & 0xFF) > (b2 & 0xFF)) // 姣旇緝鏄惁澶т簬
			return 1;
		else if ((b1 ^ b2) == 0)// 鍒ゆ柇鏄惁鐩哥瓑
			return 0;
		else
			return -1;
	}

	/**
	 * 杩欎釜鏂规硶灏嗘牴鎹甶p鐨勫唴瀹癸紝瀹氫綅鍒板寘鍚繖涓猧p鍥藉鍦板尯鐨勮褰曞锛岃繑鍥炰竴涓粷瀵瑰亸绉� 鏂规硶浣跨敤浜屽垎娉曟煡鎵俱��
	 * 
	 * @param ip
	 *            瑕佹煡璇㈢殑IP
	 * @return 濡傛灉鎵惧埌浜嗭紝杩斿洖缁撴潫IP鐨勫亸绉伙紝濡傛灉娌℃湁鎵惧埌锛岃繑鍥�-1
	 */
	private long locateIP(byte[] ip) {
		long m = 0;
		int r;
		// 姣旇緝绗竴涓猧p椤�
		readIP(ipBegin, b4);
		r = compareIP(ip, b4);
		if (r == 0)
			return ipBegin;
		else if (r < 0)
			return -1;
		// 寮�濮嬩簩鍒嗘悳绱�
		for (long i = ipBegin, j = ipEnd; i < j;) {
			m = getMiddleOffset(i, j);
			readIP(m, b4);
			r = compareIP(ip, b4);
			// log.debug(Utils.getIpStringFromBytes(b));
			if (r > 0)
				i = m;
			else if (r < 0) {
				if (m == j) {
					j -= IP_RECORD_LENGTH;
					m = j;
				} else
					j = m;
			} else
				return readLong3(m + 4);
		}
		// 濡傛灉寰幆缁撴潫浜嗭紝閭ｄ箞i鍜宩蹇呭畾鏄浉绛夌殑锛岃繖涓褰曚负鏈�鍙兘鐨勮褰曪紝浣嗘槸骞堕潪
		// 鑲畾灏辨槸锛岃繕瑕佹鏌ヤ竴涓嬶紝濡傛灉鏄紝灏辫繑鍥炵粨鏉熷湴鍧�鍖虹殑缁濆鍋忕Щ
		m = readLong3(m + 4);
		readIP(m, b4);
		r = compareIP(ip, b4);
		if (r <= 0)
			return m;
		else
			return -1;
	}

	/**
	 * 寰楀埌begin鍋忕Щ鍜宔nd鍋忕Щ涓棿浣嶇疆璁板綍鐨勫亸绉�
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private long getMiddleOffset(long begin, long end) {
		long records = (end - begin) / IP_RECORD_LENGTH;
		records >>= 1;
		if (records == 0)
			records = 1;
		return begin + records * IP_RECORD_LENGTH;
	}

	/**
	 * 缁欏畾涓�涓猧p鍥藉鍦板尯璁板綍鐨勫亸绉伙紝杩斿洖涓�涓狪PLocation缁撴瀯
	 * 
	 * @param offset
	 *            鍥藉璁板綍鐨勮捣濮嬪亸绉�
	 * @return IPLocation瀵硅薄
	 */
	private IPLocation getIPLocation(long offset) {
		try {
			// 璺宠繃4瀛楄妭ip
			ipFile.seek(offset + 4);
			// 璇诲彇绗竴涓瓧鑺傚垽鏂槸鍚︽爣蹇楀瓧鑺�
			byte b = ipFile.readByte();
			if (b == REDIRECT_MODE_1) {
				// 璇诲彇鍥藉鍋忕Щ
				long countryOffset = readLong3();
				// 璺宠浆鑷冲亸绉诲
				ipFile.seek(countryOffset);
				// 鍐嶆鏌ヤ竴娆℃爣蹇楀瓧鑺傦紝鍥犱负杩欎釜鏃跺�欒繖涓湴鏂逛粛鐒跺彲鑳芥槸涓噸瀹氬悜
				b = ipFile.readByte();
				if (b == REDIRECT_MODE_2) {
					loc.country = readString(readLong3());
					ipFile.seek(countryOffset + 4);
				} else
					loc.country = readString(countryOffset);
				// 璇诲彇鍦板尯鏍囧織
				loc.area = readArea(ipFile.getFilePointer());
			} else if (b == REDIRECT_MODE_2) {
				loc.country = readString(readLong3());
				loc.area = readArea(offset + 8);
			} else {
				loc.country = readString(ipFile.getFilePointer() - 1);
				loc.area = readArea(ipFile.getFilePointer());
			}
			return loc;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 缁欏畾涓�涓猧p鍥藉鍦板尯璁板綍鐨勫亸绉伙紝杩斿洖涓�涓狪PLocation缁撴瀯锛屾鏂规硶搴旂敤涓庡唴瀛樻槧灏勬枃浠舵柟寮�
	 * 
	 * @param offset
	 *            鍥藉璁板綍鐨勮捣濮嬪亸绉�
	 * @return IPLocation瀵硅薄
	 */
	private IPLocation getIPLocation(int offset) {
		// 璺宠繃4瀛楄妭ip
		mbb.position(offset + 4);
		// 璇诲彇绗竴涓瓧鑺傚垽鏂槸鍚︽爣蹇楀瓧鑺�
		byte b = mbb.get();
		if (b == REDIRECT_MODE_1) {
			// 璇诲彇鍥藉鍋忕Щ
			int countryOffset = readInt3();
			// 璺宠浆鑷冲亸绉诲
			mbb.position(countryOffset);
			// 鍐嶆鏌ヤ竴娆℃爣蹇楀瓧鑺傦紝鍥犱负杩欎釜鏃跺�欒繖涓湴鏂逛粛鐒跺彲鑳芥槸涓噸瀹氬悜
			b = mbb.get();
			if (b == REDIRECT_MODE_2) {
				loc.country = readString(readInt3());
				mbb.position(countryOffset + 4);
			} else
				loc.country = readString(countryOffset);
			// 璇诲彇鍦板尯鏍囧織
			loc.area = readArea(mbb.position());
		} else if (b == REDIRECT_MODE_2) {
			loc.country = readString(readInt3());
			loc.area = readArea(offset + 8);
		} else {
			loc.country = readString(mbb.position() - 1);
			loc.area = readArea(mbb.position());
		}
		return loc;
	}

	/**
	 * 浠巓ffset鍋忕Щ寮�濮嬭В鏋愬悗闈㈢殑瀛楄妭锛岃鍑轰竴涓湴鍖哄悕
	 * 
	 * @param offset
	 *            鍦板尯璁板綍鐨勮捣濮嬪亸绉�
	 * @return 鍦板尯鍚嶅瓧绗︿覆
	 * @throws IOException
	 */
	private String readArea(long offset) throws IOException {
		ipFile.seek(offset);
		byte b = ipFile.readByte();
		if (b == REDIRECT_MODE_1 || b == REDIRECT_MODE_2) {
			long areaOffset = readLong3(offset + 1);
			if (areaOffset == 0)
				return "鏈煡鍦板尯";
			else
				return readString(areaOffset);
		} else
			return readString(offset);
	}

	/**
	 * @param offset
	 *            鍦板尯璁板綍鐨勮捣濮嬪亸绉�
	 * @return 鍦板尯鍚嶅瓧绗︿覆
	 */
	private String readArea(int offset) {
		mbb.position(offset);
		byte b = mbb.get();
		if (b == REDIRECT_MODE_1 || b == REDIRECT_MODE_2) {
			int areaOffset = readInt3();
			if (areaOffset == 0)
				return "鏈煡鍦板尯";
			else
				return readString(areaOffset);
		} else
			return readString(offset);
	}

	/**
	 * 浠巓ffset鍋忕Щ澶勮鍙栦竴涓互0缁撴潫鐨勫瓧绗︿覆
	 * 
	 * @param offset
	 *            瀛楃涓茶捣濮嬪亸绉�
	 * @return 璇诲彇鐨勫瓧绗︿覆锛屽嚭閿欒繑鍥炵┖瀛楃涓�
	 */
	private String readString(long offset) {
		try {
			ipFile.seek(offset);
			int i;
			for (i = 0, buf[i] = ipFile.readByte(); buf[i] != 0; buf[++i] = ipFile
					.readByte())
				;
			if (i != 0)
				return Util.getString(buf, 0, i, "GBK");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 浠庡唴瀛樻槧灏勬枃浠剁殑offset浣嶇疆寰楀埌涓�涓�0缁撳熬瀛楃涓�
	 * 
	 * @param offset
	 *            瀛楃涓茶捣濮嬪亸绉�
	 * @return 璇诲彇鐨勫瓧绗︿覆锛屽嚭閿欒繑鍥炵┖瀛楃涓�
	 */
	private String readString(int offset) {
		try {
			mbb.position(offset);
			int i;
			for (i = 0, buf[i] = mbb.get(); buf[i] != 0; buf[++i] = mbb.get())
				;
			if (i != 0)
				return Util.getString(buf, 0, i, "GBK");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
		if (args.length == 1) {
			String ip = args[0];
			IPSeeker ipseeker = new IPSeeker();
			System.out.println(ipseeker.getCountry(ip) + ipseeker.getArea(ip));
		} else {
			System.out.println("Usage IPSeeker ip");
		}
	}
}
