setlocal
set "CURRENT_DIR=%~dp0"
cd %CURRENT_DIR%
cd ..
set "ROOT_DIR=%cd%"
"%CURRENT_DIR%\7za.exe" a -r "%ROOT_DIR%\baguix-src.zip" "pom.xml" "baguix-cms\pom.xml" "baguix-utils\pom.xml" "baguix-generator\pom.xml"
"%CURRENT_DIR%\7za.exe" a -r "%ROOT_DIR%\baguix-src.zip" "baguix-cms\src\main\java\*.*" "baguix-cms\src\main\resources\*.*"  "baguix-cms\src\main\webapp\*.*"
"%CURRENT_DIR%\7za.exe" a -r "%ROOT_DIR%\baguix-src.zip" "baguix-utils\src\main\java\*.*" "baguix-utils\src\main\resources\*.*"
"%CURRENT_DIR%\7za.exe" a -r "%ROOT_DIR%\baguix-src.zip" "baguix-generator\src\main\java\*.*" "baguix-generator\src\main\resources\*.*"