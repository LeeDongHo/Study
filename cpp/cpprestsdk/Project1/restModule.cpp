#include "restModule.h"

restAPI_JSON::restAPI_JSON(std::wstring url) : url(url) {};

void restAPI_JSON::setToken(std::wstring token)
{
	this->token = token;
}

web::json::value restAPI_JSON::Start(std::wstring userKey, const int problemId, const int numberOfElevator)
{
	web::http::client::http_client client(url);
	std::wstring uri = url + L"/start/" + userKey + L"/" + std::to_wstring(problemId) + L"/" + std::to_wstring(numberOfElevator);

	auto retval = client.request(web::http::methods::POST, uri).then([](web::http::http_response res) {
		if (res.status_code() == web::http::status_codes::OK)
			return res.extract_json().get();
		});

	try {
		retval.wait();
	}
	catch (const std::exception& e) {
		std::cout << "Error : " << e.what() << std::endl;
	}
	return retval.get();
}

web::json::value  restAPI_JSON::onCall()
{
	web::http::client::http_client client(url);
	std::wstring uri = L"/oncalls";
	web::http::http_request req(web::http::methods::GET);
	req.headers().add(L"X-Auth-Token", token);
	req.set_request_uri(uri);
	auto retval = client.request(req).then([](web::http::http_response res) {
		if (res.status_code() == web::http::status_codes::OK)
			return res.extract_json().get();
		return web::json::value();
		});
	try {
		retval.wait();
	}
	catch (const std::exception& e)
	{
		std::cout << "onCalls error : " << e.what() << std::endl;
	}
	return retval.get();
}

web::json::value  restAPI_JSON::Action(web::json::value data)
{
	web::http::client::http_client client(url);
	std::wstring uri = L"/action";
	web::http::http_request req(web::http::methods::POST);
	req.headers().add(L"X-Auth-Token", token);
	req.set_request_uri(uri);
	req.set_body(data);
	auto retval = client.request(req).then([](web::http::http_response res) {
		if (res.status_code() == web::http::status_codes::OK)
			return res.extract_json().get();
		return web::json::value();
		});
	try {
		retval.wait();
	}
	catch (const std::exception& e)
	{
		std::cout << "onCalls error : " << e.what() << std::endl;
	}
	return retval.get();
}