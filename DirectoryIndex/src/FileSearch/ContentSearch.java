package FileSearch;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentSearch {
	String target;

	public ContentSearch(String target) {
		this.target = target;
	}

	public List<PathAndToken> search(List<File> list) throws IOException {
		String content = null;
		List<PathAndToken> fileAndContent = new ArrayList<PathAndToken>();
		for (File file : list) {
			try {
				content = readFile(file.getCanonicalPath(), Charset.defaultCharset());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Pattern pattern = Pattern.compile(target);
			Matcher matcher = pattern.matcher(content);
			while (matcher.find() == true) {
				PathAndToken newPathAndToken = new PathAndToken();
				newPathAndToken.setDirectory(file.getCanonicalPath());
				newPathAndToken.setFirstToken(matcher.group(1));
				newPathAndToken.setSecondToken(matcher.group(2));
				fileAndContent.add(newPathAndToken);
			}

		}
		Collections.sort(fileAndContent);
		return fileAndContent;
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
