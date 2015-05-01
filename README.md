# wm-ue1

#Console Application usage

java -classpath wm-ue1.jar console.ConsoleApplication -<option type> <option> -<option type> <option> ...

Option types with their options:
	-web: source type is a web document
		=> option: web address of the web document (e.g. http://www.google.com)
	-file: source type is a file
		=> option: path of the file (e.g. /home/user/Documents/example.txt)
	-command: the action which is executed. Use one of the following options.
		=> getWordFrequencies: get a list of all occurring words with their absolute frequency
		=> getCharFrequencies: get a list of all occurring characters with their absolute frequency
		=> getCharPairFrquencies: get a list of all occurring character pairs with their absolute frequency
		=> getWordFrequenciesPercentage: get a list of all occurring words with their absolute frequency and relative frequency
	-stopWordFileName: whether a stop word file should be used
	 => option: path of the stop word file
	 -lowerCase: flag used when case sensitivity should not be regarded. No options needed.
	 -maxListNumber: Determines the number of units should be returned by the commands (e.g. getWordFrequencies returns a list of most maxListNumber frequent words)


