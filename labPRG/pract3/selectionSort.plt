set xrange [0:20000]
set yrange [-10:]
set xtics 2000
set ytics 10000
set xlabel "Size"
set ylabel "Microseconds"
set key left
set grid

set term pdf colour enhanced solid
set output "selectionSort.pdf"

plot "selectionSort.out" using 1:2 title "Random" with points, \
	"selectionSort.out" using 1:3 title "Ascending" with points, \
	"selectionSort.out" using 1:4 title "Descending" with points
	
pause -1 "Press ENTER to continue ... "

f(x) = a*x*x+b*x+c
fit f(x) "selectionSort.out" using 1:2 via a,b,c
replot f(x)

pause -1 "Press ENTER to continue ... "

g(x) = d*x*x+e*x+f
fit g(x) "selectionSort.out" using 1:4 via d,e,f
replot g(x)

pause -1 "Press ENTER to continue ... "

print "f(", 8**5, ") = ", f(8**5)
print "g(", 8**5, ") = ", g(8**5)


set term wxt
set output