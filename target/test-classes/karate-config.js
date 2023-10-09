function fn() {
	var environment = karate.env;
	if (!environment) {
		environment = 'dev';
	}
	
	var config = { loginUrl: 'http://localhost:8080/users' }
	
	karate.configure('connectTimeout', 5000);
	karate.configure('readTimeout', 5000);
	return config;
}