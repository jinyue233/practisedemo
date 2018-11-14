package clone;

public class DeepClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address2 address = new Address2("CH" , "SD" , "QD");
        Customer2 customer1 = new Customer2(1 , 23 , address);
        Customer2 customer2 = customer1.clone();
        customer2.getAddress2().setCity("JN");
        customer2.setID(2);
        System.out.println("customer1:"+customer1.toString());
        System.out.println("customer2:"+customer2.toString());
    }
}

class Customer2 implements Cloneable{
    public int ID;
    public int age;
    public Address2 address;
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Address2 getAddress2() {
        return address;
    }
    public void setAddress2(Address2 address) {
        this.address = address;
    }
    public Customer2(int iD, int age, Address2 address) {
        super();
        ID = iD;
        this.age = age;
        this.address = address;
    }
    @Override
    public String toString() {
        return "Customer2 [ID=" + ID + ", age=" + age + ", address=" + address
                + "]";
    }
    @Override
    public Customer2 clone() throws CloneNotSupportedException {
        Customer2 customer = (Customer2) super.clone();
        customer.address = address.clone();
        return customer;
    }
}

class Address2 implements Cloneable{
    private String country;
    private String province;
    private String city;
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public String toString() {
        return "Address2 [country=" + country + ", province=" + province
                + ", city=" + city + "]";
    }
    @Override
    public Address2 clone() throws CloneNotSupportedException {
        return (Address2) super.clone();
    }
    public Address2(String country, String province, String city) {
        super();
        this.country = country;
        this.province = province;
        this.city = city;
    }
}
//输出的结果是：
//customer1:Customer2 [ID=1, age=23, address=Address2 [country=CH, province=SD, city=JN]]
//customer2:Customer2 [ID=2, age=23, address=Address2 [country=CH, province=SD, city=JN]]
