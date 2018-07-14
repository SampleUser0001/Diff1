package diff1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static final String INPUT_FILE1 = System.getProperty("user.dir")+"\\files\\input1.txt";
	public static final String INPUT_FILE2 = System.getProperty("user.dir")+"\\files\\input2.txt";
	public static final String OUTPUT_FILE = System.getProperty("user.dir")+"\\files\\output.txt";

	public static void main(String[] main){

		ArrayList<String> allList = new ArrayList<String>();

		try {
			ArrayList<String> file1 = readFile(INPUT_FILE1 , allList);
			ArrayList<String> file2 = readFile(INPUT_FILE2 , allList);

			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(OUTPUT_FILE)));
			StringBuilder _builder = new StringBuilder();
			_builder.append("line\titem1\titem2\r\n");
			for(String str : allList){
				_builder.append(str + "\t");
				writeOrNot(_builder, str, file1).append("\t");
				writeOrNot(_builder, str, file2).append("\r\n");
			}
			writer.write(_builder.toString());
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> readFile(String filePath , ArrayList<String> allList) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
		ArrayList<String> list = new ArrayList<>();

		String line;
		while((line = reader.readLine()) != null){
			list.add(line);
			if(!allList.contains(line)){
				allList.add(line);
			}
		}
		reader.close();

		return list;

	}

	public static StringBuilder writeOrNot(StringBuilder _builder , String str , ArrayList list){
		if(list.contains(str)){
			_builder.append("○");
		} else {
			_builder.append("×");
		}
		return _builder;
	}
}
