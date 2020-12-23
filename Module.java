//Module stores all information about learning modules
//Module types, active Modules, Module page keys, Module
public class Module
{
	public static String[] MATTYPE_BUTTON_IMAGES = { "tut", "p_test", "u_test" };
		
	private String firstPageIndex = "1";
	
	private static Module activeModule;
	
	public static Module getActiveModule(){
		return activeModule;
	}
	public static boolean setActiveModule(String key){
		activeModule = Resource_Manager.modules.get(key);
		currentPage = 1;
		return activeModule != null;
	}
	public static String getCurrentPageKey(){
		return activeModule.pageKey;
	}
	public static String getNextPageKey(){
		currentPage++;
		return getCurrentPageKey() + String.valueOf(currentPage);
	}
	public static String getPreviousPageKey(){
		currentPage--;
		return getCurrentPageKey() + String.valueOf(currentPage);
	}
	public static int currentPage;
	
	public String pageKey;
	public String topic;
	public String subTopic;
	public int numPages;
	public ModuleType moduleType;
	//public ModuleCatagory moduleCatagory = ModuleCatagory.PRAC_TEST;
	
	public Module(String pageKey, String topic, String subTopic, ModuleType moduleType)
	{
		this.pageKey = pageKey;
		this.topic = topic;
		this.subTopic = subTopic;
		this.moduleType = moduleType;
	}
	public Module(String pageKey, String topic, String subTopic, ModuleType moduleType, int numPages)
	{
		this(pageKey, topic, subTopic, moduleType);
		this.numPages = numPages;
		
		if(moduleType == ModuleType.PRAC_TEST_MULTI){
			//this.moduleCatagory = ModuleCatagory.UNIT_TEST;
			for(int p = 1; p <= numPages; p++){
				String pk = pageKey + String.valueOf(p);
				GUI_Manager.guiPages.put(pk, new TestLayout(pk, topic));
			}
		}
		else if(moduleType == ModuleType.TUTORIAL){
			//this.moduleCatagory = ModuleCatagory.TUTORIAL;
			for(int p = 1; p <= numPages; p++){
				String pk = pageKey + String.valueOf(p);
				GUI_Manager.guiPages.put(pk, new TutorialLayout(pk, topic));
			}
		}
/*  		else if(moduleType == ModuleType.PRAC_TEST_RNG_SHAPE){
			GUI_Manager.guiPages.put(pageKey, new TestCountLayout(pageKey, topic, numPages));
		}  */
	}
	public Module(String pageKey, String topic, String subTopic, ModuleType moduleType, int numPages, int maxEquationValue)
	{
		this(pageKey, topic, subTopic, moduleType);
		this.numPages = numPages;
		System.out.printf("adding rng shape: %s\n", pageKey);
		GUI_Manager.guiPages.put(pageKey, new TestCountLayout(pageKey, topic, maxEquationValue, numPages));
	}
	public Module(String pageKey, String topic, String subTopic, ModuleType moduleType, int numPages, String operator, int maxEquationValue)
	{
		this(pageKey, topic, subTopic, moduleType);
		this.numPages = numPages;
		System.out.printf("adding rng text: %s\n", pageKey);
		GUI_Manager.guiPages.put(pageKey, new TestMathLayout(pageKey, topic, operator, maxEquationValue, numPages));
	}
	
	public String getGrade(){
		return pageKey.substring(1,2);
	}
	public String getType(){
		return pageKey.substring(2,3);
	}
	public String getFirstPage(){
		if(moduleType == ModuleType.TUTORIAL || moduleType == ModuleType.PRAC_TEST_MULTI){
			return pageKey + firstPageIndex;
		}
		return pageKey;
	}
}

enum ModuleType { TUTORIAL, PRAC_TEST_MULTI, PRAC_TEST_RNG, PRAC_TEST_RNG_SHAPE, UNIT_TEST }
//enum ModuleCatagory { TUTORIAL, PRAC_TEST, UNIT_TEST }
//enum Subject { GEOMETRY }
