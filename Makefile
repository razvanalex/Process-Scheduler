.PHONY: build clean run

build: tema1

run:
	java -Xmx1G MainClass ${ARGS}

tema1:
	./build
	javac *.java
	jar cfe tema1.jar MainClass MainClass.class

clean:
	rm -rf *.class *.java tema1.jar
