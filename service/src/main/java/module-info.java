module service {
	requires model;
    requires spring.beans;
    requires spring.context;

    exports cz.honestcity.service.exchange;
	exports cz.honestcity.service.gateway;
}