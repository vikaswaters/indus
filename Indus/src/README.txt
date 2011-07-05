-- WEB-INF\hibernate.reveng.xml   is used to reverse eng from DB

-- src\hibernate.cfg.xml  is auto generated furing reverse engg. It is not mandatory.But used to display the tables in the Hibernate Persp-> Configuration



GenericDao daoEvents = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean(domainBeanId);
Nullpointer in getBean(0  is due to error in the hbm config  files in the applicationContext-hibernate.xml
1. check the applicationContext-hibernate.xml file itself for typos 
2. Check is the hbm files are  OK. Typically the generation is OK. May be name clashes .


