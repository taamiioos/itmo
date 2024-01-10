<?php
session_start();
if (isset($_SESSION['history'])) {
    unset($_SESSION['history']);
}
http_response_code(200);
