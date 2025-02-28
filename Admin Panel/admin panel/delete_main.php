<?php require_once('./includes/connection.php'); ?>

<?php 

    if(isset($_GET['id'])){

        $id = $_GET['id'];

        $query = "DELETE FROM counselor WHERE id = $id ";

        $result= mysqli_query($connection , $query);

        if($result){
            //Not available the food
            header('Location: manage_main_counselor.php?counselor_deleted');
        }else{
            header('Location: manage_main_counselor.php?counselor_Not_deleted');
        }
    }

?>


<?php mysqli_close($connection); ?>