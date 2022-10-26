# connectfour
This is Game app.  
It's based on JDK-18 and JAVA-FX and turn into JAR(JAVA ARCHIVE) to use is as stand alone.

To use it as JAR file you have to do this following steps. 

Install Java on your Computer.  

If you are using Linux you have to install JDK and JavaFX modules(from openFX).   

***LINUX (UBUNTU)***  

To install JDK-18,use this command.

>     sudo apt update
    
>     java   
>sudo apt install default-jre              # version 2:1.11-72build1.   
>sudo apt install openjdk-11-jre-headless  # version 11.0.14+9-0ubuntu2.   
>sudo apt install openjdk-17-jre-headless  # version 17.0.2+8-1.   
>sudo apt install openjdk-18-jre-headless  # version 18~36ea-1.   
>sudo apt install openjdk-8-jre-headless   # version 8u312-b07-0ubuntu1.   

Copy what ever version you want to install.
EXAMPLE:- 
>     sudo apt install default-jre  
After installation complete. check it's completely install or not by this command
>     java -version
You may need the **JDK** in addition to the **JRE** in order to compile and run some specific Java-based software. To install the **JDK**, execute the following command, which will also install the **JRE**. otherwise you just need **JRE** for **JAR APP**. 
Most program only need the **JRE** (Java Runtime Environment) but some programs need the Compiler at runtime in which case you need the **JDK**. 

**NOTE: - If you have the JDK, you don't need the JRE as well.**
Follow this command for **JDK**.
>     sudo apt install default-jdk
Check JDK install.
>     javac -version

***WINDOWS***

Simply Download JDK by this link  
https://www.oracle.com/java/technologies/downloads/#jdk18-windows   
![image](https://user-images.githubusercontent.com/75372853/188181616-5abc2b3a-8f23-4ef9-bc40-5d1bc77bafdf.png)
Click any of three. recommended **x64 Installer** for easy installation.    

>After downloading from x64 Installer, Double click on installation file. 

![image](https://user-images.githubusercontent.com/75372853/188183462-3545f9c1-6a48-47ab-abc2-38b383d4bba7.png)   

>Click on next and Install it in whatever location you want.   

![image](https://user-images.githubusercontent.com/75372853/188183801-7f85d858-4000-497d-858e-f92890d2b2b5.png)

>After installation click on close. `

In lastest version of **JDK** **JavaFX** comes in but not work properly. So, you need to download **JavaFX** also via this link.    
https://openjfx.io/     

Then this will open.    
![image](https://user-images.githubusercontent.com/75372853/188194322-669d0d5b-0bf1-4248-8556-9b59626a1648.png)

>Click on **DOWNLOAD**      

>Select configuration for windows
![image](https://user-images.githubusercontent.com/75372853/188196006-9ca536bc-90c1-400b-ac18-9bcf99233b4a.png)     

>Select the Architecture based x64 or x86       

![image](https://user-images.githubusercontent.com/75372853/188196576-07dc53fb-56da-4e71-a9e5-5f668df8eb61.png)     
![image](https://user-images.githubusercontent.com/75372853/188196650-2e18043a-66b4-4522-a70d-684f5aa0775a.png)

>After Download Extract the Zip Folder in Folder you want   

>While creating the Project define the VM optin in InteliJ and as well as in VS Code        

**Note:- IN VS CODE VM option is vmArgs in launch.json**       

**In VS CODE**      
>Run > Add Configuration > after this line "request": "launch", add "vmArgs": "--module-path="**your path**" --add-modules=javafx.controls"       

**In InteliJ**      
> Run > Edit Configuration      
![image](https://user-images.githubusercontent.com/75372853/188203422-d9d13545-27a0-4b4a-b2a2-ebd0a9537a65.png)     

Then this will Open     
![image](https://user-images.githubusercontent.com/75372853/188202398-a717c2ef-d2fc-4c5a-a726-4573c148e41d.png)     

> Click on "Modify Option" > Click on Add VM Options      
![image](https://user-images.githubusercontent.com/75372853/188202990-b416ec5f-d158-4a47-957b-bade6d381455.png)     

> Then give this path "--module-path="**your path**" --add-modules=javafx.controls"     

> Click on Apply and then Ok.

Then Run this and your Program       


**After installation**      
After installing these dependencies you haow to do some stuff to make **JAR** file to run because version after **JDK-11** are have basic problem or we called we have to give **JAR files permissions** to run by double clicking on it.       

***LINUX***     
> Go to folder where **JAR File** is stroed.        

If you downloaded this Project the **JAR File** are in **TemperatureConverter/out/artifacts** folder.

> Open folder in terminal

>     ls

>     ls -n

>     chmod +x filename.jar

> Hit Enter

> Double click on JAR File and Use as executable app.

***NOTE :- IF YOU CREATED YOUR OWN PROJECT. YOU HAVE TO CREATE A FAKE MAIN CLASS OR JAVA FILE IN PROJECT AND CONNECT TO YOUR MAIN FILE BY WRITE THIS***   

> public class class_name{
    public void main(String[] args){
        Main_file_class_name.main(args);
    }
  }     
  
IF YOU DON'T GET IT PLEASE GO THROUGH THIS VIDEO DOWN THEIR.        
https://youtu.be/TR7aQZPO9jg?list=PLWNp8oG8cwHA5oEljPsrjYoJ2U0L7p7b1&t=29   

***WINDOWS***
> Create a additional **CLASS or JAVA file** which connect the **main java file** and paste this

> public class class_name{
    public void main(String[] args){
        Main_file_class_name.main(args);
    }
  }
  
> Create artifacts with library files

> Build artifacts

**For above tWo step if you don't know how to do these please go through this video**        
https://youtu.be/HuFOCEHh8Zg?list=PLWNp8oG8cwHA5oEljPsrjYoJ2U0L7p7b1&t=16       

***THESE TWO LAST STEPS " CREATE ARTIFACTS AND BUILD ARTIFACT" ARE SAME FOR LINUX ALSO TO BUILD YOUR OWN JAR FILE OF YOUR PROJECT.***       

**Aftre doing all these My program look like this.**

![image](https://user-images.githubusercontent.com/75372853/197979870-63546fed-20b6-47bb-ad1e-812a2f2bc1ce.png)
