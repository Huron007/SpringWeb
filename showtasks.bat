call .\runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo Cannot execute runcrud.bat
goto fail

:openbrowser
start firefox http://localhost:8080/crud/v1/tasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open web browser

:fail
echo.
echo There were some errors

:end
echo.
echo Work is finished