

SOURCES := $(shell find src/main/java -name "*.java")
MAIN_CLASS := Kevlin

all: kevlin_j

clean:
	rm -rf out

kevlin_j: out/jar/kevlin_j.jar

out/jar/kevlin_j.jar: ${SOURCES}
	@mkdir -p out/compile
	@mkdir -p out/jar
	javac -d out/compile $<
	cd out/compile && jar -cfe ../../$@ Kevlin *.class


