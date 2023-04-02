package lab_rab.lab2;

import lab_rab.lab2.DAO.DataTypeGetting;
import lab_rab.lab2.DAO.Serialize;
import lab_rab.lab2.DAO.Text;
import lab_rab.lab2.exceptions.DuplicateModelNameException;
import lab_rab.lab2.exceptions.NoSuchModelNameException;
import lab_rab.lab2.facade.FacadeTraffic;
import lab_rab.lab2.interfaces.ChainOfResponsibility;
import lab_rab.lab2.interfaces.IVehicle;
import lab_rab.lab2.interfaces.TransportFactory;
import lab_rab.lab2.interfaces.Visitor;
import lab_rab.lab2.observer.Gui;
import lab_rab.lab2.strategy.Analyzer;
import lab_rab.lab2.strategy.Dom;
import lab_rab.lab2.strategy.Sax;
import lab_rab.lab2.threads.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ArrayBlockingQueue;

import java.beans.IndexedPropertyChangeEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.io.*;
import java.util.Date;
import java.util.Objects;
import java.lang.reflect.Method;


public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, ClassNotFoundException, CloneNotSupportedException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InterruptedException, SAXException, ParserConfigurationException, XMLStreamException {
        //Car car = new Car("VAZ", 10);
        /* System.out.println(Vehicles.getAveragePrice(car));
        System.out.println(car.getModelPrice("Model [0]"));
        car.setModelPrice("Model [0]", 500);
        System.out.println(car.getModelPrice("Model [0]"));
        System.out.println(Arrays.toString(car.getAllModelsPrices()));
        car.setBrand("VW");
        System.out.println(car.getBrand());
        car.setModelName("Model [0]", "Model [00]");
        System.out.println(Arrays.toString(car.getAllModelsNames()));
        car.addModel("NewModel", 1000);
        Vehicles.printVehicle(car);
        car.deleteModel("NewModel");
        System.out.println(Arrays.toString(car.getAllModelsNames()));
        System.out.println(car.getModelsSize()); */

        //System.out.println(car.getModelPrice("Model [11]"));
        //car.setModelPrice("Model [2]", -200);
        //car.addModel("Model [00]", 1000); */


        //Motorcycle moto = new Motorcycle("Moto", 10);
        /* moto.setBrand("NewMoto");
        System.out.println(moto.getBrand());
        moto.setModelName("0", "Model [00]");
        System.out.println(Arrays.toString(moto.getAllModelsNames()));
        System.out.println(moto.getModelPrice("Model [00]"));
        moto.setModelPrice("Model [00]", 1000);
        System.out.println(moto.getModelPrice("Model [00]"));
        Vehicles.printPriceList(moto);
        moto.deleteModel("1");
        Vehicles.printPriceList(moto);
        moto.addModel("0", 100);
        Vehicles.printPriceList(moto);
        System.out.println(Arrays.toString(moto.getAllModelsPrices()));
        System.out.println(Vehicles.getAveragePrice(moto)); */

        //System.out.println(moto.getModelPrice("Model [11]"));
        //moto.setModelPrice("Model [2]", -200);
        //moto.addModel("aaa", 100);


        /* System.out.println("Введите Car or Motorcycle");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameOfClass = reader.readLine();
        System.out.println("Введите модель выбранного транспортного средства");
        String nameOfModel = reader.readLine();
        System.out.println("Введите число моделей");
        int numberOfModel = Integer.parseInt(reader.readLine());
        IVehicle auto = null;
        if (Objects.equals(nameOfClass, "Car"))
            auto = new Car(nameOfModel, numberOfModel);
        else auto = new Motorcycle(nameOfModel,numberOfModel);
        System.out.println("Количество моделей в массиве равно = " + auto.getModelsSize());
        System.out.println("Распечатываем модели и цены");
        Vehicles.printVehicle(auto);
        //IVehicle veh = Vehicles.readVehicle(new InputStreamReader(System.in));
        //Vehicles.writeVehicle(veh,new OutputStreamWriter(System.out)); */

        /* FileOutputStream fos = new FileOutputStream("data1.dat");
        Vehicles.outputVehicle(car,fos);
        fos.close();
        FileInputStream fis = new FileInputStream("data1.dat");
        IVehicle vehicleResult = Vehicles.inputVehicle(fis);
        System.out.println(vehicleResult.getBrand());
        System.out.println(vehicleResult.getModelsSize());
        Vehicles.printVehicle(vehicleResult);
        fis.close(); */

        /*System.out.println("Сохраним список моделей в файл");
        File file = new File("data.byte");
        long timestamp = file.lastModified();
        System.out.println("data.byte последний раз был изменен = " + new Date(timestamp));
        FileWriter fileWriter = new FileWriter("data.txt");
        Vehicles.writeVehicle(car, fileWriter);
        fileWriter.flush();
        fileWriter.close();
        FileReader fileReader = new FileReader("data.txt");
        IVehicle vehicleResult2 = Vehicles.readVehicle(fileReader);
        System.out.println(vehicleResult2.getModelsSize());
        fileReader.close(); */

        /* Car auto = new Car("auto",2);
        FileOutputStream fos2 = new FileOutputStream("auto2.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos2);
        oos.writeObject(auto);
        oos.close();
        System.out.println("Файл записан");
        FileInputStream fis2 = new FileInputStream("auto2.txt");
        ObjectInputStream ois = new ObjectInputStream(fis2);
        IVehicle motor = (IVehicle) ois.readObject();
        System.out.println("Вывод информации о машинах...");
        Vehicles.printVehicle(motor);
        ois.close(); */

        /* IVehicle veh = Vehicles.readVehicle(new InputStreamReader(System.in));
        Vehicles.writeVehicle((IVehicle) veh, new OutputStreamWriter(System.out));
        Vehicles.printVehicle(veh); */


        /* Car car = new Car("VAZ", 2);System.out.println(car);
        Car car2 = new Car("VAZ", 2);
        System.out.println(car.equals(car2));
        Car car3 = new Car("Auto", 2);
        System.out.println(car.equals(car3));
        System.out.println(car.hashCode() == car2.hashCode());
        Car cloned = car.clone();
        System.out.println(car.equals(cloned));
        car.setModelName("Model [0]", "Model 00");
        System.out.println(car);
        System.out.println(cloned);
        System.out.println(car2.hashCode() == cloned.hashCode());
        System.out.println(car3.equals(cloned));
        System.out.println(car3.hashCode() == cloned.hashCode()); */


        /* Motorcycle moto = new Motorcycle("VAZ", 2);
        System.out.println(moto);
        Motorcycle moto2 = new Motorcycle("VAZ", 2);
        System.out.println(moto.equals(moto2));
        Motorcycle moto3 = new Motorcycle("Auto", 2);
        System.out.println(moto.equals(moto3));
        System.out.println(moto.hashCode() == moto2.hashCode());
        Motorcycle cloned = moto.clone();
        moto.setModelName("1", "Model 00");
        System.out.println(moto);
        System.out.println(cloned);
        System.out.println(moto.equals(cloned));
        System.out.println(moto.hashCode() == cloned.hashCode());
        System.out.println(moto2.hashCode() == cloned.hashCode());
        System.out.println(moto3.equals(cloned)); */


        //Method setModelPriceMethod = Car.class.getMethod("setModelPrice", String.class, double.class);
        //setModelPriceMethod.invoke(car, "Model [0]", 9999);
        //Vehicles.printVehicle(car);


        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        /* System.out.println("Введите имя класса"); //lab_rab.lab2.Car
        String nameOfClass = reader.readLine();
        Class<?>  myClass = Class.forName(nameOfClass);
        System.out.println("Введите имя метода"); //setModelPrice
        String nameOfMethod = reader.readLine();
        System.out.println("Введите имя модели"); //Model [0]
        String nameOfModel = reader.readLine();
        System.out.println("Введите новую цену модели");
        String newPriceStr = reader.readLine();
        double newPrice = Double.parseDouble(newPriceStr);
        Method setMethod = myClass.getDeclaredMethod(nameOfMethod, String.class, double.class);
        setMethod.invoke(car, nameOfModel, newPrice);
        Vehicles.printVehicle(car); */


        /*Car car2 = new Car("Car", 2);
        Motorcycle moto = new Motorcycle("Moto", 2);
        IVehicle newCar = Vehicles.createByReflection(car2, "NewCar", 3);
        IVehicle newMoto = Vehicles.createByReflection(moto, "NewMoto", 3);
        System.out.println(newCar.getClass());
        System.out.println(newMoto.getClass());
        Vehicles.printVehicle(newCar);
        Vehicles.printVehicle(newMoto); */

        /* Scooter scooter = new Scooter("NewScooter", 3);
        Vehicles.printVehicle(scooter);
        System.out.println(scooter.getModelPrice("NewScooter1"));
        scooter.setModelPrice("NewScooter1", 500);
        System.out.println(scooter.getModelPrice("NewScooter1"));
        System.out.println(Arrays.toString(scooter.getAllModelsPrices()));
        scooter.setBrand("VW");
        System.out.println(scooter.getBrand());
        scooter.setModelName("NewScooter1", "NewScooter00");
        System.out.println(Arrays.toString(scooter.getAllModelsNames()));
        scooter.addModel("NewModel", 1000);
        Vehicles.printVehicle(scooter);
        scooter.deleteModel("NewModel");
        System.out.println(Arrays.toString(scooter.getAllModelsNames()));
        System.out.println(scooter.getModelsSize()); */

        /* QuadBike quadBike = new QuadBike("NewQuadBike", 2);
        Vehicles.printVehicle(quadBike);
        System.out.println(quadBike.getModelPrice("NewQuadBike1"));
        quadBike.setModelPrice("NewQuadBike1", 500);
        System.out.println(quadBike.getModelPrice("NewQuadBike1"));
        System.out.println(Arrays.toString(quadBike.getAllModelsPrices()));
        quadBike.setBrand("VW");
        System.out.println(quadBike.getBrand());
        quadBike.setModelName("NewQuadBike1", "NewQuadBike00");
        System.out.println(Arrays.toString(quadBike.getAllModelsNames()));
        quadBike.addModel("NewModel", 1000);
        Vehicles.printVehicle(quadBike);
        quadBike.deleteModel("NewModel");
        System.out.println(Arrays.toString(quadBike.getAllModelsNames()));
        System.out.println(quadBike.getModelsSize()); */

        /* Moped moped = new Moped("NewMoped", 2);
        Vehicles.printVehicle(moped);
        System.out.println(moped.getModelPrice("NewMoped1"));
        moped.setModelPrice("NewMoped1", 500);
        System.out.println(moped.getModelPrice("NewMoped1"));
        System.out.println(Arrays.toString(moped.getAllModelsPrices()));
        moped.setBrand("VW");
        System.out.println(moped.getBrand());
        moped.setModelName("NewMoped1", "NewMoped00");
        System.out.println(Arrays.toString(moped.getAllModelsNames()));
        moped.addModel("NewModel", 1000);
        Vehicles.printVehicle(moped);
        moped.deleteModel("NewModel");
        System.out.println(Arrays.toString(moped.getAllModelsNames()));
        System.out.println(moped.getModelsSize()); */


        /* Car car11 = new Car ("AAA", 3);
        Motorcycle moto11 = new Motorcycle ("BBB", 2);
        Moped moped11 = new Moped("CCC", 3);
        double aver = Vehicles.getAveragePrices(car11, moto11, moped11);
        System.out.println(aver); */

        //IVehicle veh = Vehicles.readsVehicle(new InputStreamReader(System.in));
        //Vehicles.writesVehicle((IVehicle) veh, new OutputStreamWriter(System.out));


        /* Car car = new Car("Auto", 2);
        Vehicles.printVehicle(car);

        try {
            Class<?> cl = Class.forName(args[0]);
            Class<?>[] params = new Class[]{String.class, double.class};
            Method setModelPriceMethod = cl.getMethod(args[1], params);
            setModelPriceMethod.invoke(car, args[2], Double.parseDouble(args[3]));
            Vehicles.printVehicle(car);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } */


        /* IVehicle car = new Car("NewCar", 3000);
        Thread thread11 = new Thread11(car);
        thread11.setPriority(Thread.MAX_PRIORITY);
        Thread thread12 = new Thread12(car);
        thread12.setPriority(Thread.MIN_PRIORITY);
        thread11.start();
        thread12.start(); */

        /* Car car2 = new Car("AAA", 3);
        TransportSynchronizer transportSynchronizer = new TransportSynchronizer(car2);
        Runnable thr21 = new Thread21(transportSynchronizer);
        Runnable thr22 = new Thread22(transportSynchronizer);
        Thread thread21 = new Thread(thr21);
        Thread thread22 = new Thread(thr22);
        thread21.start();
        thread22.start(); */


        /* Car car3 = new Car("BBB", 3);
        ReentrantLock reentrantLock = new ReentrantLock();
        Runnable thr31 = new Thread31ReentrantLock(car3, reentrantLock);
        Runnable thr32 = new Thread32ReentrantLock(car3, reentrantLock);
        Thread thread31 = new Thread(thr31);
        Thread thread32 = new Thread(thr32);
        thread31.start();
        thread32.start(); */


        /* ExecutorService service = Executors.newFixedThreadPool(2);
        Car car41 = new Car("Car41",2);
        Car car42 = new Car("Car42",2);
        Car car43 = new Car("Car43",2);
        Car car44 = new Car("Car44",2);
        service.execute(new Thread4(car41));
        service.execute(new Thread4(car42));
        service.execute(new Thread4(car43));
        service.execute(new Thread4(car44));
        service.shutdown(); */


        /* String[] filePaths = new String[]{"lab51.txt","lab52.txt","lab53.txt","lab54.txt","lab55.txt"};
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue <IVehicle>(1);
        for(int i =0; i< filePaths.length; i++){
            Thread5BlockingQueue transportThreadBlockingQueue = new Thread5BlockingQueue(filePaths[i], arrayBlockingQueue);
            new Thread(transportThreadBlockingQueue).start();
        }
        for(int i =0; i< filePaths.length; i++){
            Car car = (Car) arrayBlockingQueue.take();
            Vehicles.printVehicle(car);
        } */


        /* IVehicle car = Vehicles.createInstance("Car",3);
        System.out.println(car.getClass());
        Vehicles.printVehicle(car);
        Vehicles.setTransportFactory(new MotoFactory());
        IVehicle moto = Vehicles.createInstance("Moto", 2);
        System.out.println(moto.getClass());
        Vehicles.printVehicle(moto); */


        /* Motorcycle moto1 = new Motorcycle("Moto", 2);
        Motorcycle cloned = moto1.clone();
        moto1.setModelName("1", "Model 00");
        Vehicles.printVehicle(moto1);
        Vehicles.printVehicle(cloned); */


        /* Adapter adapter = new Adapter();
        adapter.writeOutputStream("First", "Second", "Third");
        try {
            InputStream inputStream = new FileInputStream("Adapter.txt");
            int content;
            while((content = inputStream.read())!=-1){
                System.out.print((char)content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } */


        /* IVehicle car = new Car("Car1", 2);
        Decorator decorator = Vehicles.getSynchronizedVehicle(car);
        for (String carName : decorator.getAllModelsNames()) {
            System.out.println(carName);
        } */

        //FacadeTraffic.runTrafficJam();



        /* IVehicle car1 = new Car("NewCar", 5);
        ChainOfResponsibility rowChain = new RowChain();
        ChainOfResponsibility columnChain = new ColumnChain();
        columnChain.setNextChain(rowChain);
        columnChain.writeVehicle(car1);
        IVehicle car2 = new Car("NewCar", 3);
        columnChain.writeVehicle(car2); */


        /* FileWriter row = new FileWriter("RowCommand.txt");
        FileWriter colm = new FileWriter("ColumnCommand.txt");
        Car car = new Car("Command", 5);
        car.setPrintCommand(new ColumnCommand());
        car.print(colm);
        car.setPrintCommand(new RowCommand());
        car.print(row); */


        /* Car car = new Car("iterator", 3);
        Car.AutoIterator iterator = car.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        } */


        /* Car car5 = new Car("Memento", 3);
        Vehicles.printVehicle(car5);
        car5.createMemento();
        car5.addModel("New", 500);
        Vehicles.printVehicle(car5);
        Car mem = car5.setMemento();
        Vehicles.printVehicle(mem); */


        //Analyzer analyzer = new Analyzer();
        //analyzer.setStrategy(new Sax(args[0], args[1]));
        //analyzer.documentParse();
        //analyzer.setStrategy(new Dom(args[0], args[1]));
        //analyzer.documentParse();


        /* Visitor visitor = new PrintVisitor();
        Motorcycle moto  = new Motorcycle("Moto",2);
        Car car = new Car("Car",3);
        moto.accept(visitor);
        car.accept(visitor); */

/*         IVehicle car = new Car("Car", 3);
        Vehicles.printVehicle(car);
        DataTypeGetting file = new Text();
        file.write(car);
        car.addModel("New Model", 100);
        file.read();
        file.print();
        Vehicles.printVehicle(car);*/
    }
}
