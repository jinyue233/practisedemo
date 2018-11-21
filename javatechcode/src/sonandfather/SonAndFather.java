package sonandfather;

/**
 * 总结：
 * 1，访问属性看声明，访问方法看实际类型（new出来的对象）-->注意这里的方法指的是非静态方法，静态方法不存在多态
 * 2，静态方法也是看声明
 * 3，静态方法和字段都存在继承，但不存在多态
 * 4，如果子类里面定义了跟父类静态方法和属性，那么这时候父类的静态方 法或属性称之为"隐藏"
 */
public class SonAndFather
{
    public static void main(String[] args)
    {
        Father a = new Father();
        Chilren b = new Chilren();
        Father c = new Chilren();
        a.getAge();
        System.out.println(a.age);
        b.getAge();
        System.out.println(b.age);
        c.getAge();
        System.out.println(c.age);
    }
}

class Father
{
    int age = 40;
    public void getAge()
    {
        System.out.println(age);
    }
}

class Chilren extends Father
{
    int age = 18;
    public void getAge()
    {
        System.out.println(age);
    }
}
/*
output:
40
40
18
18
18
40
 */