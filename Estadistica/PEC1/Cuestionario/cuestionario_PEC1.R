setwd(dir = "~/Developer/GitHub/UOC/Estadistica/PEC1/Cuestionario/")
vendes_1 <- read.csv("vendes_pac1_P_15_1.csv", sep = ";", dec = ",")
vendes_2 <- read.csv("vendes_pac1_P_15_2.csv", sep = ";", dec = ",")
vendes_3 <- read.csv("vendes_pac1_P_15_3.csv", sep = ";", dec = ",")
vendes_4 <- read.csv("vendes_pac1_P_15_4.csv", sep = ";", dec = ",")

View(vendes_1)
View(vendes_2)
View(vendes_3)
View(vendes_4)

nueve_estudiantes <- c(3,4,9,15,14,7,12,14,2)
nueve_estudiantes
mean(nueve_estudiantes)
my_sdp <- function(x){
 sqrt(mean((x - mean(x))^2))
}
round(my_sdp(nueve_estudiantes), digits = 3)
median(nueve_estudiantes)

sqrt(mean((nueve_estudiantes - mean(nueve_estudiantes)^2)))
pisos <- data.frame(ni = c(7,6,7,3), xi = c(73,78,81,76))
pisos
round(sum(pisos$xi * pisos$ni)/ sum(pisos$ni), digits = 3)
pop_sd <- function(x) {
  n <- length(x)
  sd(x) * sqrt((n - 1) / n)
}
x <- rep(pisos$xi, pisos$ni)
pop_sd(x)
median(pisos$xi)

ocho_estudiantes <- c(14,17,20,9,10,17,10,9)
round(quantile(ocho_estudiantes), digits = 3)
median(ocho_estudiantes)

horas_estudio <- data.frame(xi = c(4, 7, 14), ni = c(2, 5, 6))
x <- rep(horas_estudio$xi, horas_estudio$ni)
quantile(x)
median(x)

install.packages("ggplot2")
library(dplyr)
library(ggplot2)

vendes_4
preu <- vendes_4 %>%
  group_by(PreuAm2)
ggplot(preu, aes(x = PreuAm2)) + 
  geom_histogram()
