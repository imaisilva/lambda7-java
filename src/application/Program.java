package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			List <Employee> employees = new ArrayList<>();
			
			System.out.print("Enter salary: ");
			double salary = sc.nextDouble();
			
			String line = br.readLine();
			while (line != null) {
				String[] employeeCsv = line.split(",");
				employees.add(new Employee(employeeCsv[0], employeeCsv[1], Double.parseDouble(employeeCsv[2])));
				line = br.readLine();
			}
			
			Comparator <String> comp = (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List <String> email = employees.stream()
					.filter(emp -> emp.getSalary() > salary)
					.map(emp -> emp.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());
			System.out.println("email of people whose salary is more than 2000.00: ");
			email.forEach(System.out::println);
			
			
			double sumSalary = employees.stream()
					.filter(sal -> sal.getName().charAt(0) == 'M')
					.map(sal -> sal.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.print("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sumSalary));
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();

	}

}
