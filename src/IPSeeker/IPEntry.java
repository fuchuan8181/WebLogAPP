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



/**
 * <pre>
 * 涓�鏉P鑼冨洿璁板綍锛屼笉浠呭寘鎷浗瀹跺拰鍖哄煙锛屼篃鍖呮嫭璧峰IP鍜岀粨鏉烮P
 * </pre>
 * 
 * @author luma
 */
public class IPEntry {
    public String beginIp;
    public String endIp;
    public String country;
    public String area;
    
    /**
     * 鏋勯�犲嚱鏁�
     */
    public IPEntry() {
        beginIp = endIp = country = area = "";
    }
}
