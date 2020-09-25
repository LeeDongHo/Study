#pragma once

#include <cpprest/http_client.h>
#include <cpprest/json.h>

class restAPI_JSON {
private:
	std::wstring url;
	std::wstring token;
public:
	restAPI_JSON(std::wstring url);
	void setToken(std::wstring token);
	web::json::value Start(std::wstring userKey, int problem_id, int numberOfElevator);
	web::json::value onCall();
	web::json::value Action(web::json::value data);
	//public static restAPI_JSON& instance();		//singleton
};
