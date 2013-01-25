SOURCES		=	$(wildcard src/*.java)
CLASSES		=	$(SOURCES:.java=.class)

.SUFFIXES: .java .class

JC		=	javac
JFLAGS		=	

.java.class:
	$(JC) $(JFLAGS) -cp src/ $<

all: $(CLASSES)


clean:
	rm --force $(CLASSES)

install: 


uninstall:
