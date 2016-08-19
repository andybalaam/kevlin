

SOURCES := $(shell find src/main/java -name "*.java")
MAIN_CLASS := Kevlin

all: test_j

clean:
	rm -rf out

test_j: kevlin_j
	java -jar out/jar/kevlin_j.jar

kevlin_j: out/jar/kevlin_j.jar

out/jar/kevlin_j.jar: ${SOURCES}
	@mkdir -p out/compile
	@mkdir -p out/jar
	javac -d out/compile $<
	cd out/compile && jar -cfe ../../$@ Kevlin **.class


