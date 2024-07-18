package com.mycompany.app;

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
import unittest

class TestLogin(unittest.TestCase):

    def setUp(self):
        # Setup headless Chrome browser
        chrome_options = Options()
        chrome_options.add_argument("--headless")
        chrome_options.add_argument("--no-sandbox")
        chrome_options.add_argument("--disable-dev-shm-usage")
        self.driver = webdriver.Chrome(service=Service('path/to/chromedriver'), options=chrome_options)
        self.driver.get("http://192.168.1.188/login.php")  # Replace with the correct URL

    def test_successful_login(self):
        driver = self.driver

        email_field = driver.find_element(By.NAME, "email")
        password_field = driver.find_element(By.NAME, "password")
        submit_button = driver.find_element(By.NAME, "submit")

        email_field.send_keys("user@example.com")
        password_field.send_keys("password1234")
        submit_button.click()

        # Assert that the user is redirected to the dashboard
        self.assertIn("dashboard.php", driver.current_url)

    def test_unsuccessful_login_wrong_email(self):
        driver = self.driver

        email_field = driver.find_element(By.NAME, "email")
        password_field = driver.find_element(By.NAME, "password")
        submit_button = driver.find_element(By.NAME, "submit")

        email_field.send_keys("wrong@example.com")
        password_field.send_keys("password1234")
        submit_button.click()

        # Check for error message
        error_msg = driver.find_element(By.CLASS_NAME, "error-msg")
        self.assertIn("Login failed", error_msg.text)

    def test_unsuccessful_login_wrong_password(self):
        driver = self.driver

        email_field = driver.find_element(By.NAME, "email")
        password_field = driver.find_element(By.NAME, "password")
        submit_button = driver.find_element(By.NAME, "submit")

        email_field.send_keys("user@example.com")
        password_field.send_keys("wrongpassword")
        submit_button.click()

        # Check for error message
        error_msg = driver.find_element(By.CLASS_NAME, "error-msg")
        self.assertIn("Login failed", error_msg.text)

    def tearDown(self):
        self.driver.quit()

if __name__ == "__main__":
    unittest.main()