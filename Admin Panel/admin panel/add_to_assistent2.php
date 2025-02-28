<?php require_once('./includes/connection.php'); ?>

<?php 
    if(isset($_GET['id'])){

    $studentId = $_GET['id'];

    $query = "UPDATE students SET add_counselor = 3 WHERE  id = $studentId ";

    $result= mysqli_query($connection , $query);

    if($result){
        //Not available the record
        header('Location: manage_students.php?yes');
        
    }else{
        header('Location: manage_students.php?no');
    }
    }

?>

<?php mysqli_close($connection); ?>