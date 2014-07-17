package common.push;

public class RegionFormat {
	
	public static String ToPinyin(String regionName){
		String region="";
		if("999".equals(regionName)){
			region="HB";
		}else if("312".equals(regionName)){
			region="HB.BD";
		}else if("314".equals(regionName)){
			region="HB.CD";
		}else if("317".equals(regionName)){
			region="HB.CZ";
		}else if("310".equals(regionName)){
			region="HB.HD";
		}else if("318".equals(regionName)){
			region="HB.HS";
		}else if("316".equals(regionName)){
			region="HB.LF";
		}else if("335".equals(regionName)){
			region="HB.QH";
		}else if("311".equals(regionName)){
			region="HB.SJ";
		}else if("315".equals(regionName)){
			region="HB.TS";
		}else if("319".equals(regionName)){
			region="HB.XT";
		}else if("313".equals(regionName)){
			region="HB.ZJ";
		}else if("320".equals(regionName)){
			region="HB.HD";
		}
		return region;
	}
	
	public static String ToPinyinRegionName(String regionName){
		String region="";
		if("999".equals(regionName)){
			region="�ӱ�ʡ";
		}else if("312".equals(regionName)){
			region="����";
		}else if("314".equals(regionName)){
			region="�е�";
		}else if("317".equals(regionName)){
			region="����";
		}else if("310".equals(regionName)){
			region="����";
		}else if("318".equals(regionName)){
			region="��ˮ";
		}else if("316".equals(regionName)){
			region="�ȷ�";
		}else if("335".equals(regionName)){
			region="�ػʵ�";
		}else if("311".equals(regionName)){
			region="ʯ��ׯ";
		}else if("315".equals(regionName)){
			region="��ɽ";
		}else if("319".equals(regionName)){
			region="��̨";
		}else if("313".equals(regionName)){
			region="�żҿ�";
		}else if("320".equals(regionName)){
			region="����";
		}
		return region;
	}

}
