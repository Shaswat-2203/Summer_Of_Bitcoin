package Summer_of_Bitcoin;
import java.io.*;  
import java.util.*; 

public class summerofbitcoin {

	public static void printArrayListToFile(ArrayList<String> arrayList, String filename) throws IOException {
	    PrintWriter writer = new PrintWriter(filename);
	    for (String line : arrayList) {
	        writer.println(line);
	    }
	    writer.close();
	}
	public static void main(String[] args) throws IOException {

		
		String file="C:\\Users\\KIIT\\OneDrive\\Desktop\\mempool_copy.csv";
		BufferedReader reader=null;
		String line="";
		ArrayList<String[]> in=new ArrayList<>();
		reader=new BufferedReader(new FileReader(file));
			while((line=reader.readLine())!=null)
			{
				String[] row=line.split(",");
				in.add(row);
			}
		Comparator c=new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
				// TODO Auto-generated method stub				
				return ((Integer.parseInt(o2[1])-Integer.parseInt(o1[1]))-(Integer.parseInt(o2[2])-Integer.parseInt(o1[2])));
			}
			
		};
		
		in.remove(0);
		ArrayList<String> output=new ArrayList<>();
		Collections.sort(in, c);
		int weight=0;
        int fee=0;
        
		for (int i=0;i<in.size();i++)
		{
				if(in.get(i).length==3)
				{weight+=Integer.parseInt(in.get(i)[2]);fee+=Integer.parseInt(in.get(i)[1]);
				output.add(in.get(i)[0]);
				}
			
				if (weight>4000000)
				{
					weight=weight-Integer.parseInt(in.get(i)[2]);
					fee=fee-Integer.parseInt(in.get(i)[1]);
					output.remove(output.size()-1);
					break;
				}

		}
		printArrayListToFile(output, "block.txt");
	}

}
