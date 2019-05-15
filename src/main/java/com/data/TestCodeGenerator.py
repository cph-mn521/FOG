# -*- coding: utf-8 -*-
"""
Created on Fri May  3 10:48:23 2019

@author: Niels
"""

import os, re
# HVIS KODEN SKAL KØRES PÅ DIN PC, TILFØG DIG SELV SOM IFELSE
if(os.getcwd() not in "nille"):
    os.chdir("C:/Users/nille/Dropbox/Skole/CPHBusiness - Datamatiker/Projekter/FOG/FOG/src/main/java/com/data")
else:
    print("SPØRG OM HJÆLP, HVIS DU ER I TVIVL")
    exit()

output = open("DAOControllerTest.java",'w')

with open("imports.txt",'r') as imports:
    for l in imports:
        output.write(l);
    imports.close()

class newMethod:
    def __init__(self, name,exception, *args):
        self.name = name
        self.exceptions = exception
        self.args = args

    def create(self):
        for n in args:
            


with open("DAOController.java", 'r') as file:
    for line in file:
        if("public" in line):
            matches = re.split(" ", line)
            try:    
                name = matches[2][:matches[2].index("(")] + "Test"
                args = line[line.index("(")+1:line.index(")")]
                exceptions = line[line.index(")")+1:]
                newMethod(name, exceptions, args)
                
            except ValueError:   
                name = line
                continue
                
            #print('@Test' + '\n' + name + '\n')
                
                
            if(os.path.isfile("")):
                continue
         
            

       
