package de.geithonline.wallpaperdesigner;

public class WPDUrls {
	private static final String WEB_HOST = "http://olivergeith.bplaced.net/";
	private static final String PHP_FILE_UPLOAD = "upload.php";
	private static final String PHP_FILE_LIST = "settingslist.php";

	public static final String UPLOAD_URL_COMMUNITY_URL = WEB_HOST + "shareddesigns/" + PHP_FILE_UPLOAD;
	public static final String UPLOAD_URL_FEATURED_DESIGNS = WEB_HOST + "publisheddesigns/" + PHP_FILE_UPLOAD;

	public static final String LIST_URL_COMMUNITY_DESIGNS = WEB_HOST + "shareddesigns/" + PHP_FILE_LIST;
	public static final String LIST_URL_FEATURED_DESIGNS = WEB_HOST + "publisheddesigns/" + PHP_FILE_LIST;

	public static final String LIST_URL_FREE_PACKS = WEB_HOST + "free-packs/" + PHP_FILE_LIST;
	public static final String LIST_URL_PREMIUM_PACKS = WEB_HOST + "premium-packs/" + PHP_FILE_LIST;

}
