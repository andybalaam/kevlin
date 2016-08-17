
all: kevlin_j

kevlin_j: out/kevlin_j.jar

out/kevlin_j.jar: out/Kevlin.class
	jar -cfe $@ Kevlin -C out Kevlin.class

out/Kevlin.class: src/main/java/Kevlin.java
	@mkdir -p out
	javac -d out $<
