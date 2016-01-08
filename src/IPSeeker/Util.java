package IPSeeker;

/*
 * LumaQQ - Java QQ Client
 *
 * Copyright (C) 2004 luma <stubma@163.com>
 *                    notXX
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

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

/**
 * 宸ュ叿绫伙紝鎻愪緵涓�浜涙柟渚跨殑鏂规硶锛屾湁浜涗富瑕佹槸鐢ㄤ簬璋冭瘯鐢ㄩ�旓紝鏈変簺涓嶆槸
 * 
 * @author luma
 * @author notXX
 */
public class Util {

	/**
	 * 鏍规嵁鏌愮缂栫爜鏂瑰紡灏嗗瓧鑺傛暟缁勮浆鎹㈡垚瀛楃涓�
	 * 
	 * @param b
	 *            瀛楄妭鏁扮粍
	 * @param offset
	 *            瑕佽浆鎹㈢殑璧峰浣嶇疆
	 * @param len
	 *            瑕佽浆鎹㈢殑闀垮害
	 * @param encoding
	 *            缂栫爜鏂瑰紡
	 * @return 濡傛灉encoding涓嶆敮鎸侊紝杩斿洖涓�涓己鐪佺紪鐮佺殑瀛楃涓�
	 */
	public static String getString(byte[] b, int offset, int len,
			String encoding) {
		try {
			return new String(b, offset, len, encoding);
		} catch (UnsupportedEncodingException e) {
			return new String(b, offset, len);
		}
	}

	/**
	 * @param ip
	 *            ip鐨勫瓧鑺傛暟缁勫舰寮�
	 * @return 瀛楃涓插舰寮忕殑ip
	 */
	public static String getIpStringFromBytes(byte[] ip) {
		StringBuilder sb = new StringBuilder();
		sb.delete(0, sb.length());
		sb.append(ip[0] & 0xFF);
		sb.append('.');
		sb.append(ip[1] & 0xFF);
		sb.append('.');
		sb.append(ip[2] & 0xFF);
		sb.append('.');
		sb.append(ip[3] & 0xFF);
		return sb.toString();
	}

	/**
	 * 浠巌p鐨勫瓧绗︿覆褰㈠紡寰楀埌瀛楄妭鏁扮粍褰㈠紡
	 * 
	 * @param ip
	 *            瀛楃涓插舰寮忕殑ip
	 * @return 瀛楄妭鏁扮粍褰㈠紡鐨刬p
	 */
	public static byte[] getIpByteArrayFromString(String ip) {
		byte[] ret = new byte[4];
		StringTokenizer st = new StringTokenizer(ip, ".");
		try {
			ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

}
