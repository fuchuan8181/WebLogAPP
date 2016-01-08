package IPSeeker;

public class getAddress {
	String address_full = "";
	String address_city = "";
	String isp = "";
	IPSeeker ipseeker = new IPSeeker();
	public String get_full(String client_ip){
		if((ipseeker.getCountry(client_ip)).isEmpty() != true){
			address_full = ipseeker.getCountry(client_ip);
			}else{
				return "无法获取";
			}
		return address_full;
	}
	public String get_city(){
		if((address_full.indexOf("省") != -1)&&(address_full.indexOf("市") != -1)){
			int i = address_full.indexOf("省");
	      	int j = address_full.indexOf("市");
            address_city = address_full.substring(i+1, j);
            return address_city;
			}
		else if((address_full.indexOf("州") != -1)&&(address_full.indexOf("市") != -1)){
			int i = address_full.indexOf("州");
      	    int j = address_full.indexOf("市");
        	address_city = address_full.substring(i+1, j);
        	return address_city;
			}
		else if(address_full.indexOf("市") != -1){
			address_city = address_full.replace("市", "");
			return address_city;
			}
		else{
			address_city = address_full;
			return address_city;
		}
	}
	public String get_isp(String client_ip) {		
		if(ipseeker.getArea(client_ip).indexOf("CZ88.NET") == -1){
			isp = ipseeker.getArea(client_ip);
		}else{
			isp = "无法获取";
		}
		return isp;		
	}
}

