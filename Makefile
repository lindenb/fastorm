.PHONY:all clean
CP=/home/lindenb/.ivy2/cache/org.apache.velocity/velocity/jars/velocity-1.7.jar:/home/lindenb/.ivy2/cache/commons-lang/commons-lang/jars/commons-lang-2.4.jar:/home/lindenb/.ivy2/cache/commons-collections/commons-collections/jars/commons-collections-3.2.1.jar
all: test

test: ./bin/com/github/lindenb/codegen/model/EModel.class
	mkdir -p tmp
	java -cp ${CP}:bin com.github.lindenb.codegen.model.EModel \
		-o tmp \
		-f src/main/resource/vm/heavy.vm jeter.xml
	(cd tmp/model && ant)
	jar tvf tmp/model/dist/mjeter.jar

./bin/com/github/lindenb/codegen/model/EModel.class : ./src/main/java/com/github/lindenb/codegen/model/EModel.java
	mkdir -p bin 
	find ./bin  -name "*.class" -delete
	javac -d bin -cp ${CP} -sourcepath src/main/java $<

clean:
