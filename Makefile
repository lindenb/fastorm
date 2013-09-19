.PHONY:all clean
CP=/home/lindenb/.ivy2/cache/org.apache.velocity/velocity/jars/velocity-1.7.jar:/home/lindenb/.ivy2/cache/commons-lang/commons-lang/jars/commons-lang-2.4.jar:/home/lindenb/.ivy2/cache/commons-collections/commons-collections/jars/commons-collections-3.2.1.jar
all: test

test: tmp/com/github/lindenb/fastorm/model/EModel.class
	java -cp ${CP}:tmp com.github.lindenb.fastorm.model.EModel \
		-o tmp \
		-T src/main/resources/velocity/heavy.vm \
		src/test/resources/model01.xml
	(cd tmp/model01 && ant)
	jar tvf tmp/model01/dist/mjeter.jar

tmp/com/github/lindenb/fastorm/model/EModel.class :  src/main/java/com/github/lindenb/fastorm/model/EModel.java
	mkdir -p tmp 
	find tmp  -name "*.class" -delete
	javac -d tmp -cp ${CP} -sourcepath src/main/java $<

clean:
	rm -rf tmp