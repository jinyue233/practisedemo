package clone;

import java.io.*;

public class SerilizableForDeepClone {

    public static Object cloneObject(Object obj) throws IOException, ClassNotFoundException{
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(obj);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in =new ObjectInputStream(byteIn);
        return in.readObject();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Address3 address = new Address3("CH" , "SD" , "QD");
        Customer3 customer1 = new Customer3(1 , 23 , address);

        Customer3 customer2 = null;
        try {
            customer2 = (Customer3)cloneObject(customer1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        customer2.getAddress3().setCity("JN");
        customer2.setID(2);
        System.out.println("customer1:"+customer1.toString());
        System.out.println("customer2:"+customer2.toString());
    }
}

class Customer3 implements Serializable{
    public int ID;
    public int age;
    public Address3 address;
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
    public Address3 getAddress3() {
        return address;
    }
    public void setAddress3(Address3 address) {
        this.address = address;
    }
    public Customer3(int iD, int age, Address3 address) {
        super();
        ID = iD;
        this.age = age;
        this.address = address;
    }
    @Override
    public String toString() {
        return "Customer3 [ID=" + ID + ", age=" + age + ", address=" + address
                + "]";
    }
    @Override
    public Customer3 clone() throws CloneNotSupportedException {
        return (Customer3) super.clone();
    }
}
class Address3 implements Serializable{
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
        return "Address3 [country=" + country + ", province=" + province
                + ", city=" + city + "]";
    }

    public Address3(String country, String province, String city) {
        super();
        this.country = country;
        this.province = province;
        this.city = city;
    }
}