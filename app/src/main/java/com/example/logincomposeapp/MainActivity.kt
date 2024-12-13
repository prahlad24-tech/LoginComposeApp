package com.example.logincomposeapp

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login")
            {
                composable("login") { LoginScreen(navController) }
                composable("dashboard") { DashboardScreen(navController) }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController)
{
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showError by remember { mutableStateOf(false) }
    var showEmptyError by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Enter Login Credentials", fontSize = 30.sp, color = Color.Blue)
        Spacer(modifier = Modifier.height(26.dp))
        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            label = { Text(text = "Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                showError = false
                showEmptyError = false
                if(username.isEmpty()||password.isEmpty())
                {
                    showEmptyError = true
                }
                else if(username == "admin" && password == "password")
                {
                    navController.navigate("dashboard")
                }
                else {
                    showError = true
                }
            },
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Text(text="Login", fontSize = 20.sp)
        }
        if(showError==false && showEmptyError==false)
        {
            Text(text = "", fontSize = 20.sp)
        } else if (showError == true){
            Text(text="Invalid Credentials", fontSize = 20.sp, color = Color.Red)
        } else if (showEmptyError == true) {
            Text(text = "Fields cannnot be empty", fontSize = 20.sp, color = Color.Red)
        }
    }

}

@Composable
fun DashboardScreen(navController: NavController)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to Dashboard", fontSize = 30.sp, color = Color.Blue)

        Spacer(modifier = Modifier.height(26.dp))
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Text(text = "Back", fontSize = 20.sp)
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    val mockNavController = rememberNavController()
    LoginScreen(mockNavController)
}


@Preview
@Composable
fun DashBoardScreenPreview() {
    val mockNavController = rememberNavController()
    DashboardScreen(mockNavController)
}