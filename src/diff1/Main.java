package diff1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static final Path INPUT_FILE1 = Paths.get(System.getProperty("user.dir"),"files","input1.txt");
	public static final Path INPUT_FILE2 = Paths.get(System.getProperty("user.dir"),"files","input2.txt");
	public static final Path OUTPUT_FILE = Paths.get(System.getProperty("user.dir"),"files","output.tsv");

	public static void main(String[] main){

		ArrayList<String> allList = new ArrayList<String>();

		try {
			List<String> list1 = Files.readAllLines(INPUT_FILE1);
			List<String> list2 = Files.readAllLines(INPUT_FILE2);

			StringBuilder _builder = new StringBuilder();
			_builder.append("line\titem1\titem2\r\n");

			Stream.concat(list1.stream(), list2.stream()).distinct().forEach((line) -> {
				_builder.append(line + "\t");
				writeOrNot(_builder, line, list1).append("\t");
				writeOrNot(_builder, line, list2).append("\r\n");
			});

			BufferedWriter writer = Files.newBufferedWriter(OUTPUT_FILE,StandardCharsets.UTF_8);
			writer.write(_builder.toString());
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static StringBuilder writeOrNot(StringBuilder _builder , String str , List<String> list1){
		if(list1.contains(str)){
			_builder.append("○");
		} else {
			_builder.append("×");
		}
		return _builder;
	}
}
