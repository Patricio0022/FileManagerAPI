import { forwardRef, useState, useImperativeHandle } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

type InputButtonProps = {
  onSubmit: () => void; // function to be called when the form is submitted
};

export const InputButton = forwardRef(({ onSubmit }: InputButtonProps, ref) => {
  const [url, setUrl] = useState(''); // state to store the input value

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => { // update the url state when the input value changes
    setUrl(event.target.value);
  };

  const handleSubmitTXT = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/files/file?url=${encodeURIComponent(url)}`, {
        headers: {
          'Accept': 'text/plain',
        },
      });
      const data = await response.text();
      console.log("TXT data:", data);
    } catch (error) {
      console.error("Error fetching TXT data:", error);
    }
  };

  const handleSubmitJSON = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/files/file?url=${encodeURIComponent(url)}`, {
        headers: {
          'Accept': 'application/json',
        },
      });
      const data = await response.json();
      console.log("JSON data:", data);
    } catch (error) {
      console.error("Error fetching JSON data:", error);
    }
  };

  const handleSubmitPDF = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/files/file?url=${encodeURIComponent(url)}`, { // fetch the data from the server
        headers: {
          'Accept': 'application/pdf',
        },
      });
      const data = await response.json();
      console.log("JSON data:", data);
    } catch (error) {
      console.error("Error fetching JSON data:", error);
    }
  };


//expose the handleSubmitTXT and handleSubmitJSON functions to the parent component
  useImperativeHandle(ref, () => ({
    handleSubmitTXT,
    handleSubmitJSON,
    handleSubmitPDF
  }));

  return (
    <form 
      className="flex w-full max-w-sm items-center space-x-2"
      onSubmit={(e) => {
        e.preventDefault();
        onSubmit(); 
      }}
    >
      <Input 
        type="text" 
        placeholder="https://jsonplaceholder.typicode.com/todos" 
        value={url}
        onChange={handleChange} 
      />
      <Button type="submit">Submit</Button>
    </form>
  );
});
