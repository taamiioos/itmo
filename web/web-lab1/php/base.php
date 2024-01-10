<?php
session_start();

if (!isset($_SESSION['history'])) {
    $_SESSION['history'] = array();
}

if (isset($_POST['x'], $_POST['y'], $_POST['r'], $_POST['time'])) {
    include 'checkZone.php';

    class RequestValidator
    {
        private $params;

        function __construct($params)
        {
            $this->params = $params;
        }

        private function checkParams()
        {
            foreach ($this->params as $val) {
                if (!isset($_POST[$val])) {
                    http_response_code(400);
                    die();
                }
            }
        }

        private function checkTypeParams()
        {
            foreach ($this->params as $val) {
                if (!is_numeric($_POST[$val])) {
                    http_response_code(400);
                    die();
                }
            }
        }

        public function checkAll()
        {
            $this->checkParams();
            $this->checkTypeParams();
        }
    }

    $requestValidator = new RequestValidator(array("x", "y", "r", "time"));
    $requestValidator->checkAll();

    $y = $_POST['y'];
    $r = $_POST['r'];
    $x = $_POST['x'];
    $time_zone_offset = $_POST['time'];

    $checkZone = new CheckZone($x, $y, $r);
    $flag = $checkZone->check() ? "TRUE" : "FALSE";

    $time = date('H:i:s', time() - $time_zone_offset * 60);
    $start_time = round(microtime(true) - $_SERVER['REQUEST_TIME_FLOAT'], 6);

    $request_data = array(
        'x' => $x,
        'y' => $y,
        'r' => $r,
        'flag' => $flag,
        'start_time' => $start_time,
        'time' => $time
    );

    $_SESSION['history'][] = $request_data;
}

header('Content-Type: application/json');
$responseData = isset($_SESSION['history']) ? $_SESSION['history'] : [];
echo json_encode($responseData);


