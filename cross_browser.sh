#!/bin/bash

mvn test -DxmlFile=smoke_test_suite.xml -Dbrowser=chrome
mvn test -DxmlFile=smoke_test_suite.xml -Dbrowser=edge
mvn test -DxmlFile=smoke_test_suite.xml -Dbrowser=firefox



# cmd command to run this file is simply navigate to the project folder and then type following 2 commands in that sequence:
#                 [chmod u+x cross_browser.sh]
#                 [./cross_browser.sh]


