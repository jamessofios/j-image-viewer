name=ImageGUI.jar
src_files=Image.java ImageGUI.java
class_files=Image.class ImageGUI.class ImageGUI$1.class Image$2.class Image$3.class Image$4.class
jc=javac
jar=jar cvmf
manifest=MANIFEST.mf
.PHONY=all clean

all: classes $(name)

classes: $(src_files)
	$(jc) $^

$(name): $(class_files)
	$(jar) $(manifest) $@ $^

clean:
	$(RM) *.class *.jar
