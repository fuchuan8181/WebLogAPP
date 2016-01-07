package gloable;

public enum Month {
	
	January("Jan",1),February("Feb",2),March("Mar",3), April("Apr",4),May("May",5),June("Jun",6),
	July("Jul",7),August("Aug",8),September("Sep",9),October("Oct",10),November("Nov",11),December("Dec",12);
	
	private String name;  
    private int index;  
    
    private Month(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    // ∆’Õ®∑Ω∑®  
    public static String getName(int index) {  
        for (Month c : Month.values()) {  
            if (c.getIndex() == index) {  
                return c.name;  
            }  
        }  
        return null;  
    }  
   
    public int getIndex() {  
        return index;  
    }  
}
