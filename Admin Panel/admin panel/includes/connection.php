<?php 

    //$connecton = ('dbserver' , 'dbuser' , 'dbpassword' , 'dbname');

    $connection = mysqli_connect('localhost' , 'root' , '' , 'anxietyapplication');

    if(mysqli_connect_errno()){
        die ('Database connect fail' . mysqli_connect_error());
    }else{
        //echo "database connect successfully. ";
    }

?>