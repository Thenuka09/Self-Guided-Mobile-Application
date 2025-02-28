<?php require_once('./includes/connection.php'); ?>

<?php 

    if(isset($_GET['id'])){

        $id = $_GET['id'];

        $query = "DELETE FROM counselor WHERE id = $id ";

        $result= mysqli_query($connection , $query);

        if($result){
            //Not available the food
            header('Location: profile.php?food_deleted');
        }else{
            header('Location: profile.php?food_Not_deleted');
        }
    }

?>


<?php mysqli_close($connection); ?>