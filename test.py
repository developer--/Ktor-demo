from selenium import webdriver
import os
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

def scrape_page(url):
    options = webdriver.ChromeOptions()
    # options.add_argument('headless')
    browser = webdriver.Chrome(chrome_options=options)
    browser.get(url)
    content = browser.page_source
    # element = WebDriverWait(browser,20).until(EC.presence_of_element_located((By.CSS_SELECTOR, "tableForm")) )
    browser.close()
    return content


# https://www.copart.com/vehicleFinderSearch/?displayStr=Audi,A4,%5B2013%20TO%202017%5D&from=%2FvehicleFinder%2F&searchStr=%7B%22MISC%22:%5B%22%23MakeCode:AUDI%20OR%20%23MakeDesc:Audi%22,%22%23LotModel:A4%22,%22%23VehicleTypeCode:VEHTYPE_V%22,%22%23LotYear:%5B2013%20TO%202017%5D%22%5D,%22sortByZip%22:false,%22buyerEnteredZip%22:null,%22milesAway%22:null%7D
if __name__ == "__main__":
    result = scrape_page('https://www.copart.com/vehicleFinderSearch/?displayStr=Audi,A4,%5B2013%20TO%202017%5D&from=%2FvehicleFinder%2F&searchStr=%7B%22MISC%22:%5B%22%23MakeCode:AUDI%20OR%20%23MakeDesc:Audi%22,%22%23LotModel:A4%22,%22%23VehicleTypeCode:VEHTYPE_V%22,%22%23LotYear:%5B2013%20TO%202017%5D%22%5D,%22sortByZip%22:false,%22buyerEnteredZip%22:null,%22milesAway%22:null%7D')
    print(result)
    f = open("/Users/mrkt/Documents/server/manqana/copart.txt", "w")
    f.write(result.encode('utf-8'))
    
    if os.path.exists("copart.txt"):
        print("exist ---------****")
    else:
        print("not exist ---------****")
    