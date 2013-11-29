activiti-parsehandler
=====================

Test Project to reproduce Parse Handler issue

Deploy the project in JBOSS 7 AS. It uses JBOSS's Example Data Source (java:jboss/datasources/ExampleDS).

If you want to deploy in a different AS or use a different datasource, changes will have to be made in the pom.xml and aciiviti.cfg.xml files.


Once deployed, point your browser to http://localhost:8080/activiti-parsehandler/index.xhtml and click the 'Start Process' button.
