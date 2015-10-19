package models.actions.auth.http;

public interface HttpLoader {

	public String httpPost(String url, String postdata);
	
	public String httpGet(String url);
}
