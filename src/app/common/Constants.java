package app.common;

public class Constants {

	/** 2Share */
    public static final String UNIVERSAL_ID = "uid";    //user id basically
    public static final String MOVIE_MODEL_ID = "modelMovie";	//GAEJ 1.8.9 issue can't contains any period!!!
    public static final String CALENDAR_MODEL_ID = "modelCalendar";	//GAEJ 1.8.9 issue can't contains any period!!!
    public static final String USER_MODEL_ID = "modelUser";	//GAEJ 1.8.9 issue can't contains any period!!!
    
    //=== Actions
    public static final String ACT_UPDATE = "update";
    public static final String ACT_DELETE = "delete";
    public static final String ACT_UPGRADE = "upgrade";	//upgrade data model e.g. to a newer JPA

    //=== Movie
//	public static final String INCLUDE_EVENT = "includeEvent";
	public static final String NEXT5 = "next5";
	public static final String SCHEDULED = "scheduled";
	public static final String SHARED = "shared";
	public static final String SHARED_BY_ALL = "sharedbyall";
	public static final String ERR_NOT_AUTHENTICATED = "Not Authenticated. Permission denied.";
	public static final String ERR_NO_OWNERSHIP = "No ownership is found. Permission to update denied.";
	public static final String CH_DAILY = "d";
	public static final String CH_WEEKLY = "w";
	public static final String CH_MONTHLY = "m";
	public static final String CH_MAGIC = "bm";
	//=== Migrate
	public static final String MIGRATE = "migrate";
    public static final String MIGRATE_DELETE = "migrate_delete";
	public static final String OWNED_BY_ME = "ownedbyme";
	public static final String LEGACY_ENTITY = "LEGACY";
	public static final String MIGRATED_ENTITY = "MIGRATED";
	public static final String MIGRATE_ALL = "migrate_all";
	//=== Error
	public static final String NO_PARENT_ERR = "no_user_found";		//user registration/creation issue like duplicate ids probably
	
	/** Service Registry */
	public static final String APP_ID_MASK = "appidmask";
	public static final String STEALTH_MODE = "incog";
	public static final String TRAVERSE_MODE = "traverse";		//traverse to the final URL if there is any intermediate sid before the final sid
	public static final String PARSE_MODE = "parse";			//parse the content (e.g. into the real hyperlinks etc)
	
}