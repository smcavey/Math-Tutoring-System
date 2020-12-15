public class Module
{
	private static Module activeModule;
	
	public static Module getActiveModule(){
		return activeModule;
	}
	public static void setActiveModule(String key){
		activeModule = Resource_Manager.modules.get(key);
		currentPage = 1;
	}
	public static String getCurrentPageKey(){
		return activeModule.pageKey + String.valueOf(currentPage);
	}
	public static String getNextPageKey(){
		currentPage++;
		return getCurrentPageKey();
	}
	public static String getPreviousPageKey(){
		currentPage--;
		return getCurrentPageKey();
	}
	public static int currentPage;
	
	public String pageKey;
	public String topic;
	public String subTopic;
	public int numPages;
	
	public Module(String pageKey, String topic, String subTopic, int numPages)//, ModuleType moduleType, Subject subject, int grade)
	{
		this.pageKey = pageKey;
		this.topic = topic;
		this.subTopic = subTopic;
		this.numPages = numPages;
		
		String type = getType();
		for(int p = 1; p <= numPages; p++){
			String pk = pageKey + String.valueOf(p);
			if(type.equals("p")){
				GUI_Manager.guiPages.put(pk, new TestLayout(pk, "Some Tilte"));
			}
			else if(type.equals("t")){
				GUI_Manager.guiPages.put(pk, new TutorialLayout(pk, "Some Title"));
			}
		}
	}
	
	public String getGrade(){
		return pageKey.substring(1,2);
	}
	public String getType(){
		return pageKey.substring(2,3);
	}
}

//enum ModuleType { TUTORIAL, PRAC_TEST, UNIT_TEST }
//enum Subject { GEOMETRY }
