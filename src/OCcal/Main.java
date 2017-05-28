package OCcal;
import java.util.Scanner;
import java.lang.Math.* ;

public class Main
{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("欢迎使用Zylo工资计算器JAVA版\n\n");
		
		System.out.println("请输入你想计算的月份");
		int month = input.nextInt();
		System.out.println("你输入的月份是 "+month+"月\n");
		
		System.out.println("请输入你本月预计加班总时长");
		int ochour = input.nextInt();
		System.out.println("你输入的月份是 "+month+"月\n");
		
		System.out.println("请输入你的基本工资");
		int basesalary = input.nextInt();
		System.out.println("你输入的基本工资是"+basesalary+"元\n");
		
		int pa, la, ta, nsa, sa, ea, uf = 1;  //pa,performance award; la,living allowance; ta, transport allowance; nsa,nightshift allowance; sa,special allowance; uf, union fee; ea, environment allowance;
		int loop = 0;
		while(loop == 0){
		System.out.println("请输入你的级别(字母大写)");
		String level = input.next();
		if(level.equals("Y1")){
			pa = 300;
			la = 420;
			System.out.println("表现奖"+pa);
			System.out.println("外住补贴"+la);
			loop++;
			}
		else if(level.equals("Y2")){
			pa = 600;
			la = 700;
			System.out.println("表现奖"+pa);
			System.out.println("外住补贴"+la);
			loop++;
			}
		else{
			System.out.println("输入有误，重新输入\n");
			continue;
			}
		ta = 150;
		sa = 0;
		System.out.println("交通补贴"+ta);
		
		if(month==6 || month==7 || month==8 || month==9 || month==10){
			ea = 100;
		}
		else{ea = 0;}
		
		//if(ifnightshirt == "Y") nsa = 20 * wd;
		//System.out.println("本月是否夜班？(Y/N)");
		//String ifnightshirt = input.next();
		
		
		double hourlysalary2 = basesalary / 21.75 / 8;
		String hourlysalary1 = String.format("%.2f", hourlysalary2);
		double hourlysalary =  Double.parseDouble(hourlysalary1);
		System.out.println("时薪"+hourlysalary+"元");
		
		System.out.println("请分别输入平加，休加，节加时长，分别按回车");
		OCsalary oc = new OCsalary(input.nextInt(),input.nextInt(),input.nextInt());
		double ocsalary =( oc.getOCa()*1.5 + oc.getOCb()*2 + oc.getOCc()*3 ) * hourlysalary;
		System.out.println("加班工资"+ocsalary);
		
		System.out.println("请分别输入欠勤时长");
		Absentcost absent = new Absentcost(input.nextInt());
		double absentcost =( absent.getAbsentcost() ) * hourlysalary;
		System.out.println("欠勤扣费"+absentcost);
		
		System.out.println("请输入社保基数");
		Insurance insurance = new Insurance(input.nextInt());
		double ins = insurance.getInsurance();
			
		System.out.println("请输入公积金基数");
		ProvidentFund providentfund = new ProvidentFund(input.nextInt());
		double prof = providentfund.getProvidentFund();
		
		System.out.println("社保"+String.format("%.2f", ins));
		System.out.println("公积金"+String.format("%.2f", prof));
		
		double allsalarypre2 = basesalary + ocsalary + pa + la +ta + sa + ea - absentcost - ins - prof;
		String allsalarypre1 =String.format("%.2f",allsalarypre2);
		double allsalarypre = Double.parseDouble(allsalarypre1);
		System.out.println("应发工资"+allsalarypre);
		
		Tax tax = new Tax(allsalarypre);
		double t = tax.getTax();
		System.out.println("个人所得税为" + t + "元");
		
		double allsalarypost2 = allsalarypre - t;
		String allsalarypost1 = String.format("%.2f",allsalarypost2);
		double allsalarypost = Double.parseDouble(allsalarypost1);
		System.out.println("实发工资"+allsalarypost);
		}
	}
}

class OCsalary{
	int oca; //workday，单位元
	int ocb; //restday，单位元
	int occ; //holiday，单位元
	
	OCsalary(int oca, int ocb, int occ){
		this.oca = oca;
		this.ocb = ocb;
		this.occ = occ;
	}
	
	int getOCa(){
		return this.oca;
	}	
	int getOCb(){
		return this.ocb;
	}	
	int getOCc(){
		return this.occ;
	}
	
	void scale(int factor){
	}
}

class Absentcost{
	int absent; //欠勤时长
	
	Absentcost(int absent){
		this.absent = absent;
	}
	
	int getAbsentcost(){
		return this.absent;
	}
}

class Insurance{
	double base;
	
	Insurance(double base){
		this.base = base;
	}
	
	double getInsurance(){
		return this.base *= 0.105870588235294;
	}
}

class ProvidentFund{
	double base;

	ProvidentFund(int base){
		this.base = base;
	}

	double getProvidentFund(){
		return this.base *= 0.08;
	}
}

class Tax{
	double base;
	
	Tax(double base){
		this.base = base;
	}
	
	double getTax(){
		double tax = 0;
		//tax 必须已经赋值
		if(base <= 3500){
			tax = 0;
		}
		else if(base <=5000 && base >= 3500){
			tax = (base-3500)*0.03;
		}
		else if(base <= 8000 && base >= 5000){
			tax = (base-5000)*0.03 + 45;
		}
		return tax;
	}
}
