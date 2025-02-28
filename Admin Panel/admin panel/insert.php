<?php require_once('./includes/connection.php'); ?>

<?php 

    $email = 'counselor@gmail.com';
    $name = 'counselor';
    $password = 'counselor@123';
    $phone = '0716372861';

    $hased_password = password_hash($password, PASSWORD_DEFAULT);

    $query = "INSERT INTO counselor (email, name, password, phone_number) VALUES ('{$email}', '{$name}', '{$hased_password}', '{$phone}')";

    $result = mysqli_query($connection, $query);

    if($query){
        echo "Record added success";
    }else{
        echo "Record added faile";
    }

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
</body>
</html>
<?php mysqli_close($connection); ?>