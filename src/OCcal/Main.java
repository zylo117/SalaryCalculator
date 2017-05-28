package OCcal;
import java.util.Scanner;
import java.lang.Math.* ;

public class Main
{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("��ӭʹ��Zylo���ʼ�����JAVA��\n\n");
		
		System.out.println("���������������·�");
		int month = input.nextInt();
		System.out.println("��������·��� "+month+"��\n");
		
		System.out.println("�������㱾��Ԥ�ƼӰ���ʱ��");
		int ochour = input.nextInt();
		System.out.println("��������·��� "+month+"��\n");
		
		System.out.println("��������Ļ�������");
		int basesalary = input.nextInt();
		System.out.println("������Ļ���������"+basesalary+"Ԫ\n");
		
		int pa, la, ta, nsa, sa, ea, uf = 1;  //pa,performance award; la,living allowance; ta, transport allowance; nsa,nightshift allowance; sa,special allowance; uf, union fee; ea, environment allowance;
		int loop = 0;
		while(loop == 0){
		System.out.println("��������ļ���(��ĸ��д)");
		String level = input.next();
		if(level.equals("Y1")){
			pa = 300;
			la = 420;
			System.out.println("���ֽ�"+pa);
			System.out.println("��ס����"+la);
			loop++;
			}
		else if(level.equals("Y2")){
			pa = 600;
			la = 700;
			System.out.println("���ֽ�"+pa);
			System.out.println("��ס����"+la);
			loop++;
			}
		else{
			System.out.println("����������������\n");
			continue;
			}
		ta = 150;
		sa = 0;
		System.out.println("��ͨ����"+ta);
		
		if(month==6 || month==7 || month==8 || month==9 || month==10){
			ea = 100;
		}
		else{ea = 0;}
		
		//if(ifnightshirt == "Y") nsa = 20 * wd;
		//System.out.println("�����Ƿ�ҹ�ࣿ(Y/N)");
		//String ifnightshirt = input.next();
		
		
		double hourlysalary2 = basesalary / 21.75 / 8;
		String hourlysalary1 = String.format("%.2f", hourlysalary2);
		double hourlysalary =  Double.parseDouble(hourlysalary1);
		System.out.println("ʱн"+hourlysalary+"Ԫ");
		
		System.out.println("��ֱ�����ƽ�ӣ��ݼӣ��ڼ�ʱ�����ֱ𰴻س�");
		OCsalary oc = new OCsalary(input.nextInt(),input.nextInt(),input.nextInt());
		double ocsalary =( oc.getOCa()*1.5 + oc.getOCb()*2 + oc.getOCc()*3 ) * hourlysalary;
		System.out.println("�Ӱ๤��"+ocsalary);
		
		System.out.println("��ֱ�����Ƿ��ʱ��");
		Absentcost absent = new Absentcost(input.nextInt());
		double absentcost =( absent.getAbsentcost() ) * hourlysalary;
		System.out.println("Ƿ�ڿ۷�"+absentcost);
		
		System.out.println("�������籣����");
		Insurance insurance = new Insurance(input.nextInt());
		double ins = insurance.getInsurance();
			
		System.out.println("�����빫�������");
		ProvidentFund providentfund = new ProvidentFund(input.nextInt());
		double prof = providentfund.getProvidentFund();
		
		System.out.println("�籣"+String.format("%.2f", ins));
		System.out.println("������"+String.format("%.2f", prof));
		
		double allsalarypre2 = basesalary + ocsalary + pa + la +ta + sa + ea - absentcost - ins - prof;
		String allsalarypre1 =String.format("%.2f",allsalarypre2);
		double allsalarypre = Double.parseDouble(allsalarypre1);
		System.out.println("Ӧ������"+allsalarypre);
		
		Tax tax = new Tax(allsalarypre);
		double t = tax.getTax();
		System.out.println("��������˰Ϊ" + t + "Ԫ");
		
		double allsalarypost2 = allsalarypre - t;
		String allsalarypost1 = String.format("%.2f",allsalarypost2);
		double allsalarypost = Double.parseDouble(allsalarypost1);
		System.out.println("ʵ������"+allsalarypost);
		}
	}
}

class OCsalary{
	int oca; //workday����λԪ
	int ocb; //restday����λԪ
	int occ; //holiday����λԪ
	
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
	int absent; //Ƿ��ʱ��
	
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
		//tax �����Ѿ���ֵ
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
