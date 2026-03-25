set xrange [0:20000]
set yrange [-10:]
set xtics 20000
set ytics 10
set xlabel "Size"
set ylabel "Microseconds"
set key left
set grid

#set term pdf colour enhanced solid
#set output "selectionSort.pdf"

plot "insertionSort.out" using 1:2 title "Random" with points, \
	"insertionSort.out" using 1:3 title "Ascending" with points, \
	"insertionSort.out" using 1:4 title "Descending" with points
	
pause -1 "Press ENTER to continue ... "

f(x) = a*x*x+b*x+c
fit f(x) "insertionSort.out" using 1:3 via a,b,c
replot f(x)

pause -1 "Press ENTER to continue ... "

g(x) = d*x*x+e*x+f
fit g(x) "insertionSort.out" using 1:4 via d,e,f
replot g(x)

pause -1 "Press ENTER to continue ... "

print "f(", 800000, ") = ", f(800000)
print "g(", 800000, ") = ", g(800000)