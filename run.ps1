# Student Course Registration System - PowerShell Runner
$ErrorActionPreference = "Stop"
$rootDir = $PSScriptRoot

Write-Host "========================================================" -ForegroundColor Cyan
Write-Host "  Student Course Registration System - Build & Run" -ForegroundColor Cyan
Write-Host "========================================================" -ForegroundColor Cyan

# Verify javac and java
if (-not (Get-Command javac -ErrorAction SilentlyContinue)) {
    Write-Host "[ERROR] 'javac' not found. Please install JDK and ensure it is in your PATH." -ForegroundColor Red
    exit 1
}

# Ensure bin directory exists
$binDir = Join-Path $rootDir "bin"
if (-not (Test-Path $binDir)) {
    New-Item -ItemType Directory -Path $binDir | Out-Null
}

# Compile source files
Write-Host "Compiling Java source files..." -ForegroundColor Yellow
$javaFiles = (Get-ChildItem -Recurse -Filter *.java (Join-Path $rootDir "src")).FullName
javac -encoding UTF-8 -d $binDir $javaFiles

if ($LASTEXITCODE -eq 0) {
    Write-Host "[OK] Compilation successful." -ForegroundColor Green
    Write-Host "Launching Application..." -ForegroundColor Green
    Start-Process javaw -ArgumentList "-cp", "`"$binDir`"", "app.Main"
} else {
    Write-Host "[ERROR] Compilation failed." -ForegroundColor Red
}
